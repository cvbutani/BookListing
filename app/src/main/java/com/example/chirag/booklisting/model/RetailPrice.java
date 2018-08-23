package com.example.chirag.booklisting.model;

/**
 * BookListing
 * Created by Chirag on 22/08/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetailPrice {

    @SerializedName("amount")
    @Expose
    private double amount;

    /**
     * No args constructor for use in serialization
     */
    public RetailPrice() {
    }

    /**
     * @param amount
     */
    public RetailPrice(double amount) {
        super();
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
