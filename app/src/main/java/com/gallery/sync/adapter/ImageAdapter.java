package com.gallery.sync.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.akiniyalocts.imgur_api.ImgurClient;
import com.akiniyalocts.imgur_api.model.ImgurResponse;
import com.bumptech.glide.Glide;
import com.gallery.sync.models.Image;

import java.io.File;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Created by Paul on 5/21/2016.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageHolder> {

    private List<Image> mImages;
    private Context mContext;

    public ImageAdapter() {
        mImages = Image.listAll(Image.class);
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ImageHolder(parent);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        Image image = mImages.get(position);
        Glide.with(mContext).load(image.url).into(holder.image);
    }


    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void add(String path) {
        Image image = new Image(path, true);
        image.save();
        mImages.add(image);
        notifyItemInserted(mImages.size() - 1);
        final int position = mImages.size() - 1;

        ImgurClient.getInstance()
                .uploadImage(
                        new TypedFile("image/*", new File(path)),
                        "My Image Title",
                        "My Image Description",
                        new Callback<ImgurResponse<com.akiniyalocts.imgur_api.model.Image>>() {
                            @Override
                            public void success(ImgurResponse<com.akiniyalocts.imgur_api.model.Image> imageImgurResponse, Response response) {
                                syncImage(imageImgurResponse.data.getLink(), position);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                //Notify user of failure
                            }
                        }
                );

    }

    private void syncImage(String link, int position) {

    }

    private void imageSynced(String link, int position) {
        Image image = mImages.get(position);
        image.local = false;
        image.url = link;
        image.save();
        notifyItemChanged(position);
    }

    public void delete(int position) {
        Image image = mImages.get(position);
        image.delete();
        mImages.remove(position);
        notifyItemRemoved(position);
    }

    public Image getImage(int position) {
        return mImages.get(position);
    }
}
