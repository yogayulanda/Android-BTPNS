package com.example.tugasday2.adapter;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tugasday2.R;
import com.example.tugasday2.model.Berita;

import java.io.InputStream;
import java.util.List;

public class BeritaAdapter extends BaseAdapter {
    Context context;
    private List<Berita> list;

    public BeritaAdapter(Context context, List<Berita> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView= inflater.inflate(R.layout.news_item, null);
        }

        Berita nw = list.get(position);
        TextView titleTV = (TextView) convertView.findViewById(R.id.titleTV);
        TextView tagsTV = (TextView) convertView.findViewById(R.id.tagsTV);
        ImageView urlTV = (ImageView) convertView.findViewById(R.id.imageTV);

        titleTV.setText(nw.getJudul());
        tagsTV.setText(nw.getTags());
//        urlTV.setImageURI(Uri.parse(nw.getPhotoURL()));
        new DownloadImageTask(urlTV).execute(nw.getPhotoURL());

        return convertView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
