package com.example.chirag.booklisting.model;

/**
 * BookListing
 * Created by Chirag on 22/08/18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("volumeInfo")
    @Expose
    private VolumeInfo volumeInfo;
    @SerializedName("saleInfo")
    @Expose
    private SaleInfo saleInfo;

    /**
     * No args constructor for use in serialization
     *
     */
    public Item() {
    }

    /**
     *
     * @param volumeInfo
     * @param saleInfo
     */
    public Item(VolumeInfo volumeInfo, SaleInfo saleInfo) {
        this.volumeInfo = volumeInfo;
        this.saleInfo = saleInfo;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }
}