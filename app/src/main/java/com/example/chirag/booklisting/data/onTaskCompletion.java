package com.example.chirag.booklisting.data;

import com.example.chirag.booklisting.model.BookDetail;

import java.util.List;

/**
 * BookListing
 * Created by Chirag on 27/08/18.
 */
public interface onTaskCompletion {
    void onSuccess(BookDetail data);

    void onError(String errorMessage);
}
