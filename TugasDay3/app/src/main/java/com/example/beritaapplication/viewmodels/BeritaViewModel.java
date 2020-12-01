package com.example.beritaapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.beritaapplication.model.Berita;
import com.example.beritaapplication.model.BeritaResponse;
import com.example.beritaapplication.model.BeritasResponse;
import com.example.beritaapplication.networking.BeritasRepository;

public class BeritaViewModel extends ViewModel {
    private MutableLiveData<BeritasResponse> mutableLiveData;
    private BeritasRepository beritasRepository;
    private MutableLiveData<BeritaResponse> mutableNasabahLiveData;
//    private NasabahRepository nasabahRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        beritasRepository = BeritasRepository.getInstance();
        mutableLiveData = beritasRepository.getNasabahs("1", "10");
    }

    public LiveData<BeritasResponse> getBeritasRepository() {
        return mutableLiveData;
    }
    public void refresh(String page, String limit ){
        if (mutableLiveData != null){
            mutableLiveData = beritasRepository.getNasabahs(page, limit);
            return;
        }
        beritasRepository = BeritasRepository.getInstance();
        mutableLiveData = beritasRepository.getNasabahs("1", "10");
    }

    public LiveData<BeritaResponse> postNasabahRepository(Berita beritaPayload) {
        if (mutableNasabahLiveData == null) {
            beritasRepository = BeritasRepository.getInstance();
        }
        mutableNasabahLiveData = beritasRepository.postNasabah(beritaPayload);

        return mutableNasabahLiveData;
    }

}
