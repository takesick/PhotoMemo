package com.example.photomemo.photos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.photomemo.R;
import com.example.photomemo.addphoto.AddPhotoActivity;
import com.example.photomemo.data.PhotoData;
import com.example.photomemo.data.PhotoRepository;
import com.example.photomemo.photodetail.PhotoDetailActivity;

import java.util.List;

import static com.example.photomemo.addphoto.AddPhotoActivity.REQUEST_ADD_PHOTO;

public class PhotosActivity extends AppCompatActivity implements PhotosContract.Activity {
    private PhotosPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_act);
        PhotosFragment fragment = (PhotosFragment)
                getSupportFragmentManager().findFragmentById(R.id.photos_frag);
        mPresenter = new PhotosPresenter(PhotoRepository.getInstance(), fragment, this);
    }

    public void AddPhoto() {
        Intent intent = new Intent(this, AddPhotoActivity.class);
        startActivityForResult(intent, REQUEST_ADD_PHOTO);
    }

    public void showPhotoDetails(int index) {
        Intent intent = new Intent(this, PhotoDetailActivity.class);
        intent.putExtra(PhotoDetailActivity.EXTRA_DATA_INDEX, index);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_ADD_PHOTO)
            return;
        if (resultCode != RESULT_OK)
            return;
        mPresenter.result();
    }

}
