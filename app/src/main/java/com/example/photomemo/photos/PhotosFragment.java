package com.example.photomemo.photos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photomemo.R;
import com.example.photomemo.data.PhotoData;

import java.util.ArrayList;
import java.util.List;

public class PhotosFragment extends Fragment implements PhotosContract.View {
    private PhotosContract.Presenter mPresenter;
    private PhotosAdapter mAdapter = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new PhotosAdapter(new ArrayList<PhotoData>(0), mPhotoItemListener);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.photos_frag, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.photoRecyclerView);

        //LayoutManagerを設定
        //縦方向のリストはLinearLayoutManagerを使う
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); //縦方向に設定

        //ItemDecorationを設定
        //DividerItemDecorationはリストに罫線を引くためのItemDecoration
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());

        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(mAdapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

                    @Override
                    public boolean onMove(RecyclerView view, RecyclerView.ViewHolder holder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder holder, int direction) {
                        int index = holder.getAdapterPosition();
                        mPresenter.removePhoto(index);
                    }
                });
        touchHelper.attachToRecyclerView(recyclerView);
        FloatingActionButton fab = root.findViewById(R.id.showAddPhotoFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.AddPhoto();
            }
        });
        return root;
    }

    private final PhotosAdapter.Listener mPhotoItemListener =
        new PhotosAdapter.Listener() {
            @Override
            public void onItemClicked(int index) {
//                    mPresenter.openPhotoDetails(index);
            }
        };

    public void setPresenter(PhotosContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void showPhotos(List<PhotoData> photos) {
        mAdapter.replaceData(photos);
    }


}
