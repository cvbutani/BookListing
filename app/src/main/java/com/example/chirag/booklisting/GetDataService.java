package com.example.chirag.booklisting;

import com.example.chirag.booklisting.model.BookDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * BookListing
 * Created by Chirag on 22/08/18.
 */
public interface GetDataService {

    @GET("android")
    Call<List<BookDetail>> getAllInformation();
}
