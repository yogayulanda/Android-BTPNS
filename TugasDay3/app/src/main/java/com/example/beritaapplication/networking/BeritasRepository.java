package com.example.beritaapplication.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.beritaapplication.model.Berita;
import com.example.beritaapplication.model.BeritaResponse;
import com.example.beritaapplication.model.BeritasResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritasRepository {
    private static BeritasRepository beritasRepository;

    public static BeritasRepository getInstance(){
        if (beritasRepository == null){
            beritasRepository = new BeritasRepository();
        }
        return beritasRepository;
    }

    private BeritaApi beritaApi;

    public BeritasRepository(){
        beritaApi = RetrofitService.cteateService(BeritaApi.class);
    }

    public MutableLiveData<BeritasResponse> getNasabahs(String page, String limit){
        MutableLiveData<BeritasResponse> nasabahsData = new MutableLiveData<>();
        beritaApi.getNasabahsList(page, limit).enqueue(new Callback<BeritasResponse>() {
            @Override
            public void onResponse(Call<BeritasResponse> call,
                                   Response<BeritasResponse> response) {
                if (response.isSuccessful()){
                    nasabahsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritasResponse> call, Throwable t) {
                nasabahsData.setValue(null);
            }
        });
        return nasabahsData;
    }

    public MutableLiveData<BeritaResponse> postNasabah(Berita beritaPayload){
        MutableLiveData<BeritaResponse> nasabahData = new MutableLiveData<>();
        beritaApi.postNasabah(beritaPayload).enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(Call<BeritaResponse> call,
                                   Response<BeritaResponse> response) {
                if (response.isSuccessful()){
                    nasabahData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {
                nasabahData.setValue(null);
            }
        });
        return nasabahData;
    }

}

