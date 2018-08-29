package com.example.chirag.booklisting.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * BookListing
 * Created by Chirag on 22/08/18.
 */

public class VolumeInfo {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("authors")
    @Expose
    private List<String> authors = null;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("imageLinks")
    @Expose
    private ImageLinks imageLinks;
    @SerializedName("infoLink")
    @Expose
    private String infoLink;
    @SerializedName("averageRating")
    @Expose
    private double averageRating;

    /**
     * No args constructor for use in serialization
     */
    public VolumeInfo() {
    }

    /**
     *
     * @param title - Title of the Book
     * @param authors - Author of the Book
     * @param publisher - Publisher of the Book
     * @param imageLinks - Book cover images
     * @param infoLink - Book Info link
     * @param averageRating - Book rating
     */
    public VolumeInfo(String title, List<String> authors, String publisher, ImageLinks imageLinks, String infoLink, double averageRating) {
        super();
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.imageLinks = imageLinks;
        this.infoLink = infoLink;
        this.averageRating = averageRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}