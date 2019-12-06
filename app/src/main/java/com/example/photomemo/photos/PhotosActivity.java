package com.example.photomemo.photos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.photomemo.R;
import com.example.photomemo.addphoto.AddPhotoActivity;
import com.example.photomemo.data.PhotoRepository;

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

    public void showAddPhoto() {
        Intent intent = new Intent(this, AddPhotoActivity.class);
        startActivityForResult(intent, REQUEST_ADD_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_ADD_PHOTO)
            return;
        if (resultCode != RESULT_OK)
            return;
        /* data の getData() で選択された画像の URI が取れるので、
        それを ImageView に設定すれば画像が表示される。 */
//        Uri uri = data.getData();
        /* Layout に入れた ImageView に java コードからアクセスするには
        findViewById() を使ってインスタンスを取得すればよい。 */
//        mPresenter.result(resultCode == RESULT_OK, uri);
    }
}
