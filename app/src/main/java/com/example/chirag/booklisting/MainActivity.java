package com.example.chirag.booklisting;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chirag.booklisting.model.BookDetail;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<BookInfo>>, MainContract.View {

    private DataAdapter mAdapter;

    private static final int BOOK_LOADER_ID = 1;

    private View mProgressBar;

    private TextView mEmptyTextView;

    private static final String BOOK_URL = "https://www.googleapis.com/books/v1/volumes?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView bookListView = findViewById(R.id.listView);

        mProgressBar = findViewById(R.id.loading_spinner);
        mEmptyTextView = findViewById(R.id.emptyView);

        bookListView.setEmptyView(mEmptyTextView);

        mAdapter = new DataAdapter(this, new ArrayList<BookInfo>());

        bookListView.setAdapter(mAdapter);

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookInfo link = (BookInfo) mAdapter.getItem(position);

                Uri linkUri = Uri.parse(link.getmBookUrl());

                Intent webIntent = new Intent(Intent.ACTION_VIEW, linkUri);
                startActivity(webIntent);
            }
        });

//        ConnectivityManager cm =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//
//        boolean isConnected = activeNetwork != null &&
//                activeNetwork.isConnectedOrConnecting();
//
//        if (isConnected) {
//            LoaderManager loadManager = getLoaderManager();
//            loadManager.initLoader(BOOK_LOADER_ID, null, this);
//        } else {
//            mProgressBar.setVisibility(View.GONE);
//            mEmptyTextView.setText(R.string.no_internet);
//        }

        MainPresenter presenter = new MainPresenter();
        presenter.attachView(this);
    }

    @Override
    public Loader<List<BookInfo>> onCreateLoader(int i, Bundle args) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String value = sharedPreferences.getString(
                getString(R.string.settings_min_key),
                getString(R.string.settings_min_default));
        Uri baseUri = Uri.parse(BOOK_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", value);
        uriBuilder.appendQueryParameter("maxResults", "20");
        Log.i(MainActivity.this.getClass().getName(), uriBuilder.toString());
        return new BookLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<BookInfo>> loader, List<BookInfo> data) {
        mProgressBar.setVisibility(View.GONE);
        mEmptyTextView.setText(R.string.no_books);
        mAdapter.clear();
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<BookInfo>> loader) {
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResult(BookDetail data) {
//        Log.i("GOT SOMETHING", "LET'S SEE" + data.getItems().get(0).getVolumeInfo().getTitle());

    }

    @Override
    public void onError(String errorMessage) {

    }
}
