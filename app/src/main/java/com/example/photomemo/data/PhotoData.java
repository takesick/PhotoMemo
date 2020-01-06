package com.example.photomemo.data;

import android.net.Uri;

public class PhotoData {
    private final Uri mImageUri;
    private final String mMemo;
    private String mFileName;
    private long mSize;

    public PhotoData(Uri uri, String memo) {
        mImageUri = uri;
        mMemo = memo;
    }
    public Uri getImage() {
        return mImageUri;
    }
    public String getMemo() {
        return mMemo;
    }
    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }
}
