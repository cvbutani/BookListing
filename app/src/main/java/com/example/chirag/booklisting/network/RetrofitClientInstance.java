package com.example.chirag.booklisting.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * BookListing
 * Created by Chirag on 22/08/18.
 */
public class RetrofitClientInstance {

    private static Retrofit mRetrofit;
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?maxResults=10&q=";

    public static Retrofit getRetrofitInstance() {
        if (mRetrofit == null) {
            mRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
