package com.ahmfarisi.kulinerkita.Model;

import java.util.List;

public class ModelResponse {
    private String kode, pesan;
    private List<ModelKuliner> data;
    private List<ModelPengguna> dataPengguna;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelKuliner> getData() {
        return data;
    }

    public List<ModelPengguna> getDataPengguna() {
        return dataPengguna;
    }
}
