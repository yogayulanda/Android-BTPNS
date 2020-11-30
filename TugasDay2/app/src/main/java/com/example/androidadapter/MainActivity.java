package com.example.androidadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidadapter.adapter.NasabahAdapter;
import com.example.androidadapter.model.Nasabah;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView nasabahListView;
    TextView refreshTextView;
    NasabahAdapter nasabahAdapter;
    private List<Nasabah> listNasabah= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();

    }
    void findViewById(){
        nasabahListView = (ListView) findViewById(R.id.nasabahListView);
        refreshTextView = (TextView) findViewById(R.id.refreshTextView);
    }
    void onClickGroup(){
        refreshTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nasabah newNasabah = new Nasabah();
                newNasabah.setNama("Wati");
                newNasabah.setAlamat("Jakarta selatan");
                newNasabah.setSaldo(new Double(250000));
                listNasabah.add(newNasabah);

                newNasabah = new Nasabah();
                newNasabah.setNama("Budi");
                newNasabah.setAlamat("Jakarta Utara");
                newNasabah.setSaldo(new Double(150000));
                listNasabah.add(newNasabah);

                nasabahAdapter.notifyDataSetChanged();


            }
        });
    }
    void initData(){
        //listNasabah = new ArrayList();
        //listNasabah.clear();
        nasabahAdapter = new NasabahAdapter(getApplicationContext(),listNasabah);
        nasabahListView.setAdapter(nasabahAdapter);
        nasabahAdapter.notifyDataSetChanged();
    }
}
