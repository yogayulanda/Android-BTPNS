package com.example.beritaapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beritaapplication.R;
import com.example.beritaapplication.model.Berita;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.NasabahViewHolder> {

    Context context;
    ArrayList<Berita> beritas;

    public BeritaAdapter(Context context, ArrayList<Berita> beritas) {
        this.context = context;
        this.beritas = beritas;
    }

    @NonNull
    @Override
    public BeritaAdapter.NasabahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nasabah, parent, false);
        return new  NasabahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdapter.NasabahViewHolder holder, int position) {
        holder.titleTv.setText(beritas.get(position).getTitle());
        holder.categoryTv.setText(beritas.get(position).getCategory());
        UrlImageViewHelper.setUrlDrawable(holder.urlTv, beritas.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return beritas.size();
    }

    public class NasabahViewHolder extends RecyclerView.ViewHolder{

        TextView titleTv;
        TextView categoryTv;
        ImageView urlTv;

        public NasabahViewHolder(@NonNull View
                                         itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.titleTextView);
            categoryTv = itemView.findViewById(R.id.categoryTextView);
            urlTv = itemView.findViewById(R.id.beritaImageView);
        }
    }



}

