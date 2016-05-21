package com.gallery.sync.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gallery.sync.models.ImageModel;

import java.util.List;

/**
 * Created by Paul on 5/21/2016.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageHolder> {

    private List<ImageModel> mImages;

    public ImageAdapter() {
        mImages = ImageModel.listAll(ImageModel.class);
        for (int i = 0; i <= 100; i++)
            mImages.add(new ImageModel());
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
}
