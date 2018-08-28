package com.example.chirag.booklisting;

import com.example.chirag.booklisting.model.BookDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * BookListing
 * Created by Chirag on 22/08/18.
 */
public interface GetDataService {

    @GET("volumes?q=android")
    Call<BookDetail> getAllInformation();
}
