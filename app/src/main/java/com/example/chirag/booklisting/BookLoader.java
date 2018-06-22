package com.example.chirag.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<BookInfo>> {

    private String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<BookInfo> loadInBackground() {
        if(mUrl == null){
            return null;
        }

        return BookQuery.fetchBookData(mUrl);
    }
}
