package com.example.chirag.booklisting;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<BookInfo>> {

    private DataAdapter mAdapter;

    private static final int EARTHQUAKE_LOADER_ID = 1;

    private static final String BOOK_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView bookListView = findViewById(R.id.listView);

        mAdapter = new DataAdapter(this, new ArrayList<BookInfo>());
        bookListView.setAdapter(mAdapter);

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            LoaderManager loadManager = getLoaderManager();
            loadManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);
        }
    }


    @Override
    public Loader<List<BookInfo>> onCreateLoader(int i, Bundle args) {
        return new BookLoader(this, BOOK_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<BookInfo>> loader, List<BookInfo> data) {
        mAdapter.clear();
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<BookInfo>> loader) {
        mAdapter.clear();
    }
}
