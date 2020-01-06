package com.example.photomemo.photodetail;

public interface PhotoDetailContract {
    interface View {
        void setPresenter(PhotoDetailPresenter photoDetailPresenter);
//        void showPhoto(Uri photoImage);
    }

    interface Presenter {
//        void start();
//        void savePhoto(String memo);
//        void addPhotoToList(boolean result);
    }

    interface Activity {
//        void showPicker();
//        void finishWithResult(boolean result);
    }
}