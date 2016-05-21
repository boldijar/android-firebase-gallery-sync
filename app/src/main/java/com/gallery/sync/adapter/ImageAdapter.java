package com.gallery.sync.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gallery.sync.models.Image;

import java.util.List;

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
