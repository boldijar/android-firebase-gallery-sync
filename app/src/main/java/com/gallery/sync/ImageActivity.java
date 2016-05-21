package com.gallery.sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    private static final String KEY_IMG = "kmg";

    public static Intent createIntent(Context context, String url) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(KEY_IMG, url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Glide.with(this).load(getIntent().getStringExtra(KEY_IMG)).into((ImageView) findViewById(R.id.image));
    }
}
