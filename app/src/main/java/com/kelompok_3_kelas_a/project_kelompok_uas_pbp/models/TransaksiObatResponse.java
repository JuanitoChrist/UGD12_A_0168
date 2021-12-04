package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiObatResponse {
    private String message;
    @SerializedName("data")
    private List<TransaksiObatModels> transaksiObatModelsList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TransaksiObatModels> getTransaksiObatModelsList() {
        return transaksiObatModelsList;
    }

    public void setTransaksiObatModelsList(List<TransaksiObatModels> transaksiObatModelsList) {
        this.transaksiObatModelsList = transaksiObatModelsList;
    }
}
