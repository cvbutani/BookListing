package com.example.chirag.booklisting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter {

    public DataAdapter(@NonNull Context context, @NonNull ArrayList<BookInfo> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View infoDisplay = convertView;

        if (infoDisplay == null) {
            infoDisplay = LayoutInflater.from(getContext()).inflate(R.layout.activity_description, parent, false);
        }

        BookInfo book = (BookInfo) getItem(position);

        TextView bookName = infoDisplay.findViewById(R.id.book_title);
        TextView bookAuthor = infoDisplay.findViewById(R.id.book_author);
        TextView bookPublisher = infoDisplay.findViewById(R.id.book_publisher);
        RatingBar bookRating = infoDisplay.findViewById(R.id.book_rating);
        TextView bookPrice = infoDisplay.findViewById(R.id.book_price);
        ImageView bookImage = infoDisplay.findViewById(R.id.book_image);
        if (book != null) {

            bookName.setText(book.getmBookName());
            bookAuthor.setText(book.getmBookAuthor());
            bookPublisher.setText(book.getmBookPublisher());

            if (book.getmBookRating() == 0) {
                bookRating.setVisibility(View.GONE);
            } else {
                bookRating.setVisibility(View.VISIBLE);
                bookRating.setRating((float) book.getmBookRating());
            }

            bookPrice.setText(book.getmBookPrice());

            new ImageURL(bookImage).execute(book.getmBookImage());
        }
        return infoDisplay;
    }
}
