package com.example.chirag.booklisting.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookDetail {

    @SerializedName("items")
    private List<Item> items = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public BookDetail() {
    }

    public BookDetail(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}