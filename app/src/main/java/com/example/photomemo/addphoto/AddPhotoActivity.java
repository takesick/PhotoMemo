package com.example.photomemo.addphoto;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.photomemo.R;
import com.example.photomemo.data.PhotoData;
import com.example.photomemo.data.PhotoRepository;
import com.example.photomemo.photos.PhotosActivity;

public class AddPhotoActivity extends AppCompatActivity implements AddPhotoContract.Activity {
    private AddPhotoPresenter mPresenter;
    public static final int REQUEST_ADD_PHOTO = 1;
    private final int REQUEST_PICK_PHOTO = 2;

//    private AddPhotoFragment mFragment; onActivityResult()の結果処理はPresenterに任せるため不要になる。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_photo_act);
        AddPhotoFragment fragment = (AddPhotoFragment)
                getSupportFragmentManager().findFragmentById(R.id.add_photo_frag);
        mPresenter = new AddPhotoPresenter(PhotoRepository.getInstance(), fragment, this);
    }

    /* ギャラリーを開く intent 呼び出し */
    public void showPicker() {
        //intentとは意図：新しく欲しいものの条件(他のアプリに伝える条件)
        //Intent.~意図(伝える条件)の編集ができる
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);//.ACTION_OPEN_DOCUMENT：ストレージ内のドキュメントプロバイダ内のものを条件として指定
        intent.addCategory(Intent.CATEGORY_OPENABLE);//CATEGORY_OPENABLE開けるものを指定
        intent.setType("image/jpeg");//imageフォルダのjpegを指定
        /* REQUEST_PICK_PHOTO(REQUEST_CODE) は最初に定義されている値。
        写真選択リクエストの意味の変数名にしておくとよい。
        結果が欲しいので ForResult の方を使う */
        startActivityForResult(intent, REQUEST_PICK_PHOTO);//引数：(出来上がった条件, 意図の送信元のActivityのidみたいなもの)
    }

    /* 結果を受け取るための callback 関数 */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_PICK_PHOTO)
            return;
        if (resultCode != RESULT_OK)
            return;
        /* data の getData() で選択された画像の URI が取れるので、
        それを ImageView に設定すれば画像が表示される。 */
        Uri uri = data.getData();
        /* Layout に入れた ImageView に java コードからアクセスするには
        findViewById() を使ってインスタンスを取得すればよい。 */
        mPresenter.result(resultCode == RESULT_OK, uri);
    }

    public void finishWithResult(boolean result) {
        Intent intent = new Intent(this, PhotosActivity.class);
        setResult((result) ? RESULT_OK : RESULT_CANCELED, intent);
        finish();
    }
}
