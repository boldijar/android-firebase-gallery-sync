package com.gallery.sync.models;

import com.orm.SugarRecord;

/**
 * Created by Paul on 5/21/2016.
 */
public class ImageModel extends SugarRecord {
    public String url;
    public boolean local = true;

    public ImageModel(String url, boolean local) {
        this.url = url;
        this.local = local;
    }

    public ImageModel() {
    }

    public ImageModel(String url) {
        this(url,false);
    }
}
