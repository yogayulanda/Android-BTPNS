package com.example.androidpulsa.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.androidpulsa.model.Pulsa;
import com.example.androidpulsa.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PulsaRepositories {
    private static PulsaRepositories pulsaRepositories;

    public static PulsaRepositories getInstance(){
        if (pulsaRepositories==null){
            pulsaRepositories=new PulsaRepositories();
        }
        return pulsaRepositories;
    }

    private PulsaApi pulsaApi;

    public PulsaRepositories(){
        pulsaApi=RetrofitService.createService(PulsaApi.class);
    }

    public MutableLiveData<PulsaResponse> getPulsa(){
        MutableLiveData<PulsaResponse> pulsaData = new MutableLiveData<>();
        pulsaApi.getPulsa()
                .enqueue(new Callback<PulsaResponse>() {
                    @Override
                    public void onResponse(Call<PulsaResponse> call, Response<PulsaResponse> response) {
                        if (response.isSuccessful()){
                            Log.v("LogGetPulsa: ",response.body().toString());
                            pulsaData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<PulsaResponse> call, Throwable t) {
                        Log.v("LogErrorFetch: ",t.getMessage());
                        pulsaData.setValue(null);
                    }
                });
        return pulsaData;
    }

    public MutableLiveData<PulsaResponse> buyPulsa(Pulsa pulsa){
        MutableLiveData<PulsaResponse> pulsaData=new MutableLiveData<>();
        pulsaApi.buyPulsa(pulsa)
                .enqueue(new Callback<PulsaResponse>() {
                    @Override
                    public void onResponse(Call<PulsaResponse> call, Response<PulsaResponse> response) {
                        if (response.isSuccessful()){
                            Log.v("PostData: ",response.body().toString());
                            pulsaData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<PulsaResponse> call, Throwable t) {
                        Log.v("ErrorPostData: ",t.getMessage());
                        pulsaData.setValue(null);
                    }
                });
        return pulsaData;
    }
}

