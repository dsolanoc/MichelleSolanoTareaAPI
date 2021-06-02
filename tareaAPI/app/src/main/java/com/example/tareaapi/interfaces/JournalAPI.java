package com.example.tareaapi.interfaces;

import com.example.tareaapi.models.Journal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JournalAPI {

    @GET("issues.php")
    public Call<List<Journal>> find(@Query("j_id") String j_id);

}
