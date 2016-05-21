package com.gallery.sync.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gallery.sync.models.Image;

import java.util.List;

/**
 * Created by Paul on 5/21/2016.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageHolder> {

    private List<Image> mImages;

    public ImageAdapter() {
        mImages = Image.listAll(Image.class);
        for (int i = 0; i <= 100; i++)
            mImages.add(new Image());
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageHolder(parent);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {

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
}
