package com.example.photomemo.photodetail;

import com.example.photomemo.data.PhotoRepository;

public class PhotoDetailPresenter implements PhotoDetailContract.Presenter {
    private final PhotoRepository mPhotosRepository;
    private final PhotoDetailContract.View mPhotosView;
    private final PhotoDetailContract.Activity mPhotosActivity;

    public PhotosPresenter(PhotoRepository photosRepository,
                           PhotoDetailContract.View PhotoDetailView,
                           PhotoDetailContract.Activity dataIndex) {

        mPhotosRepository = photosRepository;
        mPhotosView = PhotoDetailView;
        mPhotosActivity = PhotoDetailActivity;
        mPhotosView.setPresenter(this);
    }

    public void removePhoto(int index) {
        mPhotosRepository.removePhoto(index);
    }

    public void showPhotoDetail() {
        mPhotoDetailActivity.showPhotoDetail();
    }

    public void result() {
        List<PhotoData> photos = mPhotosRepository.getPhotos();
        mPhotosView.showPhotos(photos);
    }

}