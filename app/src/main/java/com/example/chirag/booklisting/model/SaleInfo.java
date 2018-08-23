package com.example.chirag.booklisting.model;

/**
 * BookListing
 * Created by Chirag on 22/08/18.
 */

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleInfo {

    @SerializedName("retailPrice")
    @Expose
    private RetailPrice retailPrice;

    /**
     * No args constructor for use in serialization
     */
    public SaleInfo() {
    }

    /**
     *
     * @param retailPrice
     */
    public SaleInfo(RetailPrice retailPrice) {
        this.retailPrice = retailPrice;
    }

    public RetailPrice getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(RetailPrice retailPrice) {
        this.retailPrice = retailPrice;
    }
}
