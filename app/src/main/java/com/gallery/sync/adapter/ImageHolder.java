package com.gallery.sync.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gallery.sync.R;

/**
 * Created by Paul on 5/21/2016.
 */
public class ImageHolder extends RecyclerView.ViewHolder {

    public ImageView image;

    public ImageHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
        image = (ImageView) itemView;
    }


}
