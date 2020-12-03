package com.example.androidpulsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.androidpulsa.adapter.PulsaAdapter;
import com.example.androidpulsa.model.Pulsa;
import com.example.androidpulsa.viewmodel.PulsaViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Pulsa> arrTemp =new ArrayList<>();
    private PulsaAdapter pulsaAdapter;
    private PulsaViewModel pulsaViewModel;
    private List<Pulsa> pulsaList;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pulsaRecyclerView)
    RecyclerView list_pulsa;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.viewfocus)
    View viewfocus;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.layout_rincian)
    RelativeLayout layout_rincian;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ic_close)
    AppCompatImageView ic_close;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_payment)
    AppCompatTextView tv_payment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_pulsa)
    AppCompatTextView tv_pulsa;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.layoutPulsa)
    CoordinatorLayout layoutPulsa;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_pay)
    LinearLayoutCompat btn_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        pulsaViewModel = ViewModelProviders.of(this).get(PulsaViewModel.class);
        if (pulsaAdapter==null){
            pulsaAdapter=new PulsaAdapter(MainActivity.this,arrTemp,layout_rincian,
                    viewfocus,ic_close,tv_payment,layoutPulsa,tv_pulsa,btn_pay,pulsaViewModel);
            list_pulsa.setLayoutManager(new GridLayoutManager(this,2));
            list_pulsa.setAdapter(pulsaAdapter);
            list_pulsa.setHasFixedSize(true);
            list_pulsa.setItemAnimator(new DefaultItemAnimator());
            list_pulsa.setNestedScrollingEnabled(true);
        } else {
            pulsaAdapter.notifyDataSetChanged();
        }
        pulsaViewModel.init();
        pulsaViewModel.getPulsa().observe(this,pulsaResponse -> {
            pulsaList=pulsaResponse.getData();
            arrTemp.clear();
            arrTemp.addAll(pulsaList);
            pulsaAdapter.notifyDataSetChanged();
        });
    }
}