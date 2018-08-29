package com.example.chirag.booklisting.mainactivity;

import android.content.Intent;
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

import com.example.chirag.booklisting.adapter.DataAdapter;
import com.example.chirag.booklisting.R;
import com.example.chirag.booklisting.model.BookDetail;
import com.example.chirag.booklisting.model.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private DataAdapter mAdapter;

    private View mProgressBar;

    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView bookListView = findViewById(R.id.listView);

        mProgressBar = findViewById(R.id.loading_spinner);

        mAdapter = new DataAdapter(this, new ArrayList<Item>());

        bookListView.setAdapter(mAdapter);

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item link = (Item) mAdapter.getItem(position);

                Uri linkUri = Uri.parse(link.getVolumeInfo().getInfoLink());

                Intent webIntent = new Intent(Intent.ACTION_VIEW, linkUri);
                startActivity(webIntent);
            }
        });

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
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError(String errorMessage) {
        Log.i(this.getClass().getSimpleName(), "ERROR GETTING BOOK DETAIL");
    }
}
