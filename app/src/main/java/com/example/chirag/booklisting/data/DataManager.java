package com.example.chirag.booklisting.data;

/**
 * BookListing
 * Created by Chirag on 22/08/18.
 */
public class DataManager {
    private static DataManager sDataManager;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (sDataManager == null) {
            sDataManager = new DataManager();
        }
        return sDataManager;
    }
}
