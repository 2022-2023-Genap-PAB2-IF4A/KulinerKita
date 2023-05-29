package com.ahmfarisi.kulinerkita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmfarisi.kulinerkita.API.APIRequestData;
import com.ahmfarisi.kulinerkita.API.RetroServer;
import com.ahmfarisi.kulinerkita.Adapter.AdapterKuliner;
import com.ahmfarisi.kulinerkita.Model.ModelKuliner;
import com.ahmfarisi.kulinerkita.Model.ModelResponse;
import com.ahmfarisi.kulinerkita.R;
import com.ahmfarisi.kulinerkita.Utility.KendaliLogin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvKuliner;
    private FloatingActionButton fabTambah, fabLogout;
    private TextView tvWelcome;
    private ProgressBar pbKuliner;
    private RecyclerView.Adapter adKuliner;
    private RecyclerView.LayoutManager lmKuliner;
    private List<ModelKuliner> listKuliner = new ArrayList<>();
    KendaliLogin KL = new KendaliLogin(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(KL.isLogin(KL.keySP_username) == true){
            setContentView(R.layout.activity_main);

            rvKuliner = findViewById(R.id.rv_kuliner);
            fabTambah = findViewById(R.id.fab_tambah);
            fabLogout = findViewById(R.id.fab_logout);
            pbKuliner = findViewById(R.id.pb_kuliner);
            tvWelcome = findViewById(R.id.tv_welcome);

            lmKuliner = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvKuliner.setLayoutManager(lmKuliner);

            tvWelcome.setText("Selamat Datang " + KL.getPref(KL.keySP_nama_lengkap));

            fabTambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, TambahActivity.class));
                }
            });

            fabLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KL.setPref(KL.keySP_username, null);
                    KL.setPref(KL.keySP_nama_lengkap, null);
                    KL.setPref(KL.keySP_email, null);
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            });
        }
        else{
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveKuliner();
    }

    public void retrieveKuliner(){
        pbKuliner.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse>  proses = ARD.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listKuliner = response.body().getData();

                adKuliner = new AdapterKuliner(MainActivity.this, listKuliner);
                rvKuliner.setAdapter(adKuliner);
                adKuliner.notifyDataSetChanged();

                pbKuliner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbKuliner.setVisibility(View.GONE);
            }
        });
    }
}