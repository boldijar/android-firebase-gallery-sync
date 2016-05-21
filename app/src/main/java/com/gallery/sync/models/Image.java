package com.gallery.sync.models;

import com.orm.SugarRecord;

/**
 * Created by Paul on 5/21/2016.
 */
public class Image extends SugarRecord {
    public String url;
    public boolean local;

    public Image(String url, boolean local) {
        this.url = url;
        this.local = local;
    }

    public Image() {
    }

    public Image(String url) {
        this(url, true);
    }
}
