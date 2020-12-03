package com.example.androidpulsa.networking;

import com.example.androidpulsa.model.Pulsa;
import com.example.androidpulsa.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PulsaApi {

    @GET("product")
    Call<PulsaResponse> getPulsa();

    @POST("pulsa")
    Call<PulsaResponse> buyPulsa(@Body Pulsa pulsa);

}
