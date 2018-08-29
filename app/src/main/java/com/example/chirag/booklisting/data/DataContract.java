package com.example.chirag.booklisting.data;

/**
 * BookListing
 * Created by Chirag on 27/08/18.
 */
public interface DataContract {
    void getNewBatchOfData(String searchQuery, onTaskCompletion callback);
}
