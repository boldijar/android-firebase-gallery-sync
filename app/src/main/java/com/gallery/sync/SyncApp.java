package com.gallery.sync;

import com.akiniyalocts.imgur_api.Constants;
import com.akiniyalocts.imgur_api.ImgurClient;
import com.orm.SugarApp;

/**
 * Created by Paul on 5/21/2016.
 */
public class SyncApp extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
        ImgurClient.initialize("0d5b15494d19060");
    }
}
