package com.example.chirag.booklisting.model;

/**
 * BookListing
 * Created by Chirag on 22/08/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageLinks {

    @SerializedName("smallThumbnail")
    @Expose
    private String smallThumbnail;

    /**
     * No args constructor for use in serialization
     */
    public ImageLinks() {
    }

    /**
     * @param smallThumbnail
     */
    public ImageLinks(String smallThumbnail) {
        super();
        this.smallThumbnail = smallThumbnail;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }
}