package com.example.photomemo.photos;

import com.example.photomemo.data.PhotoData;

import java.util.List;

public interface PhotosContract {
    interface View {
        void setPresenter(Presenter presenter);
        void showPhotos(List<PhotoData> photos);
    }

    interface Presenter {
        void removePhoto(int index);
        void AddPhoto();
        void result();
    }

    interface Activity {
        void AddPhoto();
    }
}
