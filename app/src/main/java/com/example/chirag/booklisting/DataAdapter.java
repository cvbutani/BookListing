package com.example.chirag.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

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
        bookName.setText(book.getmBookName());

        TextView bookAuthor = infoDisplay.findViewById(R.id.book_author);
        bookAuthor.setText(book.getmBookAuthor());

        TextView bookPublisher = infoDisplay.findViewById(R.id.book_publisher);
        bookPublisher.setText(book.getmBookPublisher());

        RatingBar bookRating = infoDisplay.findViewById(R.id.book_rating);
        bookRating.setRating((float) book.getmBookRating());

        TextView bookPrice = infoDisplay.findViewById(R.id.book_price);

        if (book.getmBookPrice() == 0) {
            bookPrice.setText("Not For Sale");
        } else {
            bookPrice.setText(String.valueOf(book.getmBookPrice()));
        }
        return infoDisplay;
    }
}
