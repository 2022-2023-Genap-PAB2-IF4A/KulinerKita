package com.ahmfarisi.kulinerkita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ahmfarisi.kulinerkita.R;

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yAsal, yDeskripsiSingkat;
    private EditText etNama, etAsal, etDeskripsiSingkat;
    private Button btnUbah;
    private String nama, asal, deskripsiSingkat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yAsal = ambil.getStringExtra("xAsal");
        yDeskripsiSingkat = ambil.getStringExtra("xDeskripsiSingkat");

        etNama = findViewById(R.id.et_nama);
        etAsal = findViewById(R.id.et_asal);
        etDeskripsiSingkat = findViewById(R.id.et_deskripsi_singkat);
        btnUbah = findViewById(R.id.btn_ubah);

        etNama.setText(yNama);
        etAsal.setText(yAsal);
        etDeskripsiSingkat.setText(yDeskripsiSingkat);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                asal = etAsal.getText().toString();
                deskripsiSingkat = etDeskripsiSingkat.getText().toString();

                if(nama.trim().isEmpty()){
                    etNama.setError("Nama Tidak Boleh Kosong");
                }
                else if(asal.trim().isEmpty()){
                    etAsal.setError("Asal Tidak Boleh Kosong");
                }
                else if(deskripsiSingkat.trim().isEmpty()){
                    etDeskripsiSingkat.setError("Deskripsi Singkat Tidak Boleh Kosong");
                }
                else{

                }
            }
        });
    }
}