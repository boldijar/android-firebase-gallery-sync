package com.gallery.sync;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.gallery.sync.adapter.ImageAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalleryActivity extends ImageLoadActivity {

    @Bind(R.id.gallery_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.gallery_toolbar)
    Toolbar mToolbar;

    private ImageAdapter mAdapter = new ImageAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.gallery_fab)
    void fabClicked() {
        loadPhoto();
    }

    @Override
    public void imageLoaded(String imagePath) {
        Toast.makeText(GalleryActivity.this, imagePath, Toast.LENGTH_SHORT).show();

    }
}
