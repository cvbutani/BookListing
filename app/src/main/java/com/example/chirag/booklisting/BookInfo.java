package com.example.chirag.booklisting;

public class BookInfo {
    private String mBookName;
    private String mBookAuthor;
    private String mBookPublisher;
    private double mBookRating;
    private double mBookPrice;
    private String mBookUrl;

    public BookInfo(String mBookName, String mBookAuthor, String mBookPublisher, String url, double mBookRating, double mBookPrice) {
        this.mBookName = mBookName;
        this.mBookAuthor = mBookAuthor;
        this.mBookPublisher = mBookPublisher;
        this.mBookRating = mBookRating;
        this.mBookPrice = mBookPrice;
        this. mBookUrl = url;
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

    public double getmBookPrice() {
        return mBookPrice;
    }

    public String getmBookUrl() {
        return mBookUrl;
    }
}
