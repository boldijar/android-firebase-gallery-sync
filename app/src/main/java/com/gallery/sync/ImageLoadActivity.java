package com.gallery.sync;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Paul on 5/21/2016.
 */
public class ImageLoadActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 1;
    private String mImagePath;

    public void loadPhoto() {
        final Intent intent = new Intent().setType("image/*").setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent,
                getResources().getString(R.string.select_profile_picture)), SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || null == data.getData()) {
            return;
        }
        final Uri uri = data.getData();
        if ("content".equals(uri.getScheme())) {

            Cursor cursor = getContentResolver()
                    .query(uri,
                            new String[]{android.provider.MediaStore.Images.ImageColumns.DATA},
                            null,
                            null,
                            null);
            if (cursor != null) {
                cursor.moveToFirst();
                mImagePath = cursor.getString(0);
                cursor.close();
            }
        } else {
            mImagePath = uri.getPath();
        }

        if (!TextUtils.isEmpty(mImagePath)) {
            File imgFile = new File(mImagePath);
            if (imgFile.exists()) {
                imageLoaded(mImagePath);
            }
        } else {
            Toast.makeText(this, R.string.error_picture, Toast.LENGTH_SHORT).show();
        }
    }

    public void imageLoaded(String imagePath) {

    }


}
