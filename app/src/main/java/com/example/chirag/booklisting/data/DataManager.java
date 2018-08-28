package com.example.chirag.booklisting.data;

/**
 * BookListing
 * Created by Chirag on 22/08/18.
 */
public class DataManager implements DataContract {
    private static DataManager sDataManager;
    private static DataUseCase mDataUseCase;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (sDataManager == null) {
            sDataManager = new DataManager();
            mDataUseCase = new DataUseCase();
        }
        return sDataManager;
    }

    @Override
    public void getNewBatchOfData(onTaskCompletion callback) {
        mDataUseCase.getData(callback);
    }
}
