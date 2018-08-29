package com.example.chirag.booklisting.adapter;

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

import com.example.chirag.booklisting.R;
import com.example.chirag.booklisting.model.BookDetail;
import com.example.chirag.booklisting.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class
DataAdapter extends ArrayAdapter {

    public DataAdapter(@NonNull Context context, List<Item> objects) {
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

        Item book = (Item) getItem(position);

        TextView bookName = infoDisplay.findViewById(R.id.book_title);
        TextView bookAuthor = infoDisplay.findViewById(R.id.book_author);
        TextView bookPublisher = infoDisplay.findViewById(R.id.book_publisher);
        RatingBar bookRating = infoDisplay.findViewById(R.id.book_rating);
        TextView bookPrice = infoDisplay.findViewById(R.id.book_price);
        ImageView bookImage = infoDisplay.findViewById(R.id.book_image);
        if (book != null) {

            bookName.setText(book.getVolumeInfo().getTitle());
            bookAuthor.setText(book.getVolumeInfo().getAuthors().get(0));
            bookPublisher.setText(book.getVolumeInfo().getPublisher());

            if (book.getVolumeInfo().getAverageRating() == 0) {
                bookRating.setVisibility(View.GONE);
            } else {
                bookRating.setVisibility(View.VISIBLE);
                bookRating.setRating((float) book.getVolumeInfo().getAverageRating());
            }

            String filler;
            if (book.getSaleInfo().getRetailPrice() == null || book.getSaleInfo().getRetailPrice().getAmount() == 0) {
                filler = "Free";
            } else {
                filler = "CAD $" + book.getSaleInfo().getRetailPrice().getAmount();
            }

            bookPrice.setText(filler);

            Picasso.get().load(book.getVolumeInfo().getImageLinks().getSmallThumbnail()).into(bookImage);
        }
        return infoDisplay;
    }
}
