package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PenggunaResponse {

    private String message;
    @SerializedName("data")
    private List<PenggunaModels> penggunaList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PenggunaModels> getPenggunaList() {
        return penggunaList;
    }

    public void setPenggunaList(List<PenggunaModels> penggunaList) {
        this.penggunaList = penggunaList;
    }
}
