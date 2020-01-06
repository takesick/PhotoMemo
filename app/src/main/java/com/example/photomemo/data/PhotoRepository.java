package com.example.photomemo.data;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class PhotoRepository {

    private static PhotoRepository mInstance = null;
    private final List<PhotoData> mPhotos;
    private Uri mTemporaryPhotoUri;

    private PhotoRepository() {
        mPhotos = new ArrayList<>();
    }
    static public PhotoRepository getInstance() {
        if (mInstance == null)
            mInstance = new PhotoRepository();
        return mInstance;
    }
    public void savePhoto(String memo) {
        mPhotos.add(new PhotoData(mTemporaryPhotoUri, memo));
        mTemporaryPhotoUri = null;
    }

    public void removePhoto(int index) {
        mPhotos.remove(index);
    }

    public void setTemporaryPhoto(Uri uri) {
        mTemporaryPhotoUri = uri;
    }

    public List<PhotoData> getPhotos() {
        return mPhotos;
    }
}
