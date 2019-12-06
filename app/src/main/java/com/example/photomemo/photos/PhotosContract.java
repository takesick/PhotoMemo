package com.example.photomemo.photos;

import com.example.photomemo.data.PhotoData;

import java.util.List;

public interface PhotosContract {
    interface View {
        void setPresenter(Presenter presenter);

    }

    interface Presenter {
        void removePhoto(int index);
        void showAddPhoto();
//        void replaceData(List<PhotoData> photos);
    }

    interface Activity {
        void showAddPhoto();
    }
}
