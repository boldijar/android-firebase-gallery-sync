package com.gallery.sync;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

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

        setupClickEvents();
    }

    private void setupClickEvents() {
        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(mRecyclerView);
        itemClickSupport.setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, final int position, View v) {
                PopupMenu popupMenu = new PopupMenu(GalleryActivity.this, v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.options_delete) {
                            mAdapter.delete(position);
                        }
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.image_options);
                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        ItemClickSupport.removeFrom(mRecyclerView);
        super.onDestroy();
    }

    @OnClick(R.id.gallery_fab)
    void fabClicked() {
        loadPhoto();
    }

    @Override
    public void imageLoaded(String imagePath) {
        mAdapter.add(imagePath);
    }
}
