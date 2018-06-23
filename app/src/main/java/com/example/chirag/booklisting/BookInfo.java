package com.example.chirag.booklisting;

public class BookInfo {
    private String mBookName;
    private String mBookAuthor;
    private String mBookPublisher;
    private double mBookRating;
    private String mBookPrice;
    private String mBookUrl;
    private String mBookImage;

    public BookInfo(String mBookName, String mBookAuthor, String mBookPublisher, String url, double mBookRating, String mBookPrice, String mBookImage) {
        this.mBookName = mBookName;
        this.mBookAuthor = mBookAuthor;
        this.mBookPublisher = mBookPublisher;
        this.mBookRating = mBookRating;
        this.mBookPrice = mBookPrice;
        this.mBookUrl = url;
        this.mBookImage = mBookImage;
    }

    public String getmBookName() {
        return mBookName;
    }

    public String getmBookAuthor() {
        return mBookAuthor;
    }

    public String getmBookPublisher() {
        return mBookPublisher;
    }

    public double getmBookRating() {
        return mBookRating;
    }

    public String getmBookPrice() {
        return mBookPrice;
    }

    public String getmBookUrl() {
        return mBookUrl;
    }

    public String getmBookImage() {
        return mBookImage;
    }
}
