package com.example.chirag.booklisting.mainactivity;

import com.example.chirag.booklisting.model.BookDetail;

/**
 * BookListing
 * Created by Chirag on 27/08/18.
 */
public class MainContract {
    interface View {
        void onResult(BookDetail data);

        void onError(String errorMessage);
    }

    interface Presenter {
        void getData(String searchQuery);

        void attachView(MainContract.View view);
    }
}
