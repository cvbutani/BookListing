package com.example.chirag.booklisting;

import android.annotation.SuppressLint;
import android.content.Context;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class
DataAdapter extends ArrayAdapter {

    public DataAdapter(@NonNull Context context, @NonNull ArrayList<BookInfo> objects) {
        super(context, 0, objects);
    }

    @SuppressLint("SetTextI18n")
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

            String filler;
            if (book.getmBookPrice().equals("0")) {
                filler = "Free";
            } else {
                filler = "CAD $" + book.getmBookPrice();
            }

            bookPrice.setText(filler);

            new ImageURL(bookImage).execute(book.getmBookImage());
        }
        return infoDisplay;
    }
}
