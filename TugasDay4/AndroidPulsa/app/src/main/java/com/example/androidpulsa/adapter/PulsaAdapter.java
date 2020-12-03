package com.example.androidpulsa.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidpulsa.add_pulsa;
import com.example.androidpulsa.model.Pulsa;
import com.example.androidpulsa.viewmodel.PulsaViewModel;
import com.example.androidpulsa.R;

import java.util.ArrayList;

import okhttp3.internal.Util;

public class PulsaAdapter extends RecyclerView.Adapter<PulsaAdapter.PulsaViewHolder> {

    Context context;
    RelativeLayout layout_rincian;
    ArrayList<Pulsa> list;
    View viewfocus;
    AppCompatImageView ic_close;

    ProgressDialog progressDialog;

    AppCompatTextView tv_payment,tv_pulsa;
    CoordinatorLayout layoutPulsa;
    LinearLayoutCompat btn_pay;
    PulsaViewModel pulsaViewModel;

    public PulsaAdapter(Context context, ArrayList<Pulsa> list, RelativeLayout layout_rincian,View viewfocus,
                        AppCompatImageView ic_close,AppCompatTextView tv_payment,CoordinatorLayout layoutPulsa,
                        AppCompatTextView tv_pulsa,LinearLayoutCompat btn_pay, PulsaViewModel pulsaViewModel) {
        this.layout_rincian=layout_rincian;
        this.layoutPulsa=layoutPulsa;
        this.tv_payment=tv_payment;
        this.tv_pulsa=tv_pulsa;
        this.ic_close=ic_close;
        this.viewfocus=viewfocus;
        this.btn_pay=btn_pay;
        this.context = context;
        this.pulsaViewModel = pulsaViewModel;
        this.list = list;
    }

    @NonNull
    @Override
    public PulsaAdapter.PulsaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        return new PulsaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PulsaAdapter.PulsaViewHolder holder, int position) {
        int pulsa = (int) list.get(position).getNominal();
        int price = (int) list.get(position).getPrice();
        String nom ="";
        if(String.valueOf(pulsa).length()==6){
            nom=String.valueOf(pulsa).substring(0,3);
        } else {
            nom=String.valueOf(pulsa).substring(0,2);
        }
        holder.tv_nominal.setText(nom);
        holder.tv_nominal2.setText(".000");
        holder.linear_nominal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.expand(layout_rincian,2);
                viewfocus.setVisibility(View.VISIBLE);
                viewfocus.animate().alpha(0.5f);
                layoutPulsa.animate().alpha(0.5f);
                tv_pulsa.setText("Pulsa "+pulsa);
                tv_payment.setText("Rp. "+price);
            }
        });
        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.collapse(layout_rincian,2);
                viewfocus.setVisibility(View.GONE);
                layoutPulsa.animate().alpha(1f);
            }
        });
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showProgressDialog(progressDialog,context);
                Pulsa pulsa1 = new Pulsa(list.get(position).getCode(),list.get(position).getNominal());
                pulsaViewModel.buyPulsa(pulsa1)
                        .observe((LifecycleOwner) context, pulsaResponse -> {
                            if (pulsaResponse.getCode()==200){
                                Util.dismissProgressDialog(progressDialog);
                                Intent intent = new Intent(context, add_pulsa.class);
                                context.startActivity(intent);
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PulsaViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView tv_nominal,tv_nominal2;
        LinearLayoutCompat linear_nominal;

        public PulsaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nominal=itemView.findViewById(R.id.tv_nominal);
            tv_nominal2=itemView.findViewById(R.id.tv_nominal2);
            linear_nominal=itemView.findViewById(R.id.linear_nominal);



        }

    }
}

