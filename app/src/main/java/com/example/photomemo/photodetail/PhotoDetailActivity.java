package com.example.photomemo.photodetail;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.photomemo.R;
import com.example.photomemo.data.PhotoRepository;
import com.example.photomemo.photos.PhotosActivity;

import static com.example.photomemo.addphoto.AddPhotoActivity.REQUEST_ADD_PHOTO;

public class PhotoDetailActivity extends AppCompatActivity implements PhotoDetailContract.Activity {
    public static final String EXTRA_DATA_INDEX = "DATA_INDEX";
    private PhotoDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int dataIndex = getIntent().getIntExtra(EXTRA_DATA_INDEX, -1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_detail_act);
        PhotoDetailFragment fragment = (PhotoDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.photo_detail_frag);
        mPresenter = new PhotoDetailPresenter(PhotoRepository.getInstance(), fragment, dataIndex);
    }

    public void showPhotoDetail(int index) {
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

    public void finishWithResult(boolean result) {
        Intent intent = new Intent(this, PhotosActivity.class);
        setResult((result) ? RESULT_OK : RESULT_CANCELED, intent);
        finish();
    }
}