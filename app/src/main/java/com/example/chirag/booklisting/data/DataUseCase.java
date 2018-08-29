package com.example.chirag.booklisting.data;

import android.support.annotation.NonNull;

import com.example.chirag.booklisting.GetDataService;
import com.example.chirag.booklisting.model.BookDetail;
import com.example.chirag.booklisting.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * BookListing
 * Created by Chirag on 27/08/18.
 */
public class DataUseCase {
    public DataUseCase() {
    }

    void getData(String searchQuery, final onTaskCompletion callback) {
        GetDataService service= RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<BookDetail>  call = service.getAllInformation(searchQuery);
        call.enqueue(new Callback<BookDetail>() {
            @Override
            public void onResponse(@NonNull Call<BookDetail> call, @NonNull Response<BookDetail> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<BookDetail> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
