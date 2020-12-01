package com.example.beritaapplication.networking;

import com.example.beritaapplication.model.Berita;
import com.example.beritaapplication.model.BeritaResponse;
import com.example.beritaapplication.model.BeritasResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;

public interface BeritaApi {
    @GET("nasabah")
    Call<BeritasResponse> getNasabahsList(@Query("page") String page,
                                          @Query("limit") String limit);

    @POST("nasabah")
    Call<BeritaResponse> postNasabah(@Body Berita body);

}
