package com.example.chirag.booklisting;

import com.example.chirag.booklisting.data.DataManager;
import com.example.chirag.booklisting.data.onTaskCompletion;
import com.example.chirag.booklisting.model.BookDetail;

import java.util.List;

/**
 * BookListing
 * Created by Chirag on 27/08/18.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mCallback;
    private DataManager mDataManager;

    MainPresenter() {
        mDataManager = DataManager.getInstance();
    }

    @Override
    public void getData() {
        mDataManager.getNewBatchOfData(new onTaskCompletion() {
            @Override
            public void onSuccess(List<BookDetail> data) {
                mCallback.onResult(data);
            }

            @Override
            public void onError(String errorMessage) {
                mCallback.onError(errorMessage);
            }
        });
    }

    @Override
    public void attachView(MainContract.View view) {
        mCallback = view;
        getData();
    }
}
