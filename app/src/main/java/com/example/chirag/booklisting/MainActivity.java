package com.example.chirag.booklisting;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ListView;
import android.widget.TextView;

import com.example.chirag.booklisting.model.BookDetail;
import com.example.chirag.booklisting.model.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private DataAdapter mAdapter;

    private View mProgressBar;

    private TextView mEmptyTextView;

    MainPresenter mPresenter;

    private static final String BOOK_URL = "https://www.googleapis.com/books/v1/volumes?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView bookListView = findViewById(R.id.listView);

        mProgressBar = findViewById(R.id.loading_spinner);
        mEmptyTextView = findViewById(R.id.emptyView);

        bookListView.setEmptyView(mEmptyTextView);
        mAdapter = new DataAdapter(this, new ArrayList<Item>());

        bookListView.setAdapter(mAdapter);

        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String searchQuery =
                PreferenceManager.getDefaultSharedPreferences(this)
                        .getString(getString(R.string.settings_min_key), getString(R.string.settings_min_default));
        mPresenter.getData(searchQuery);

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
        mAdapter.addAll(data.getItems());

    }

    @Override
    public void onError(String errorMessage) {

    }
}
