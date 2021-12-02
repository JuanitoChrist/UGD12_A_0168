package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendaftaranResponse {
    private String message;
    @SerializedName("pendaftaran")
    private List<PendaftaranModels> pendaftaranModelsList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PendaftaranModels> getPendaftaranModelsList() {
        return pendaftaranModelsList;
    }

    public void setPendaftaranModelsList(List<PendaftaranModels> pendaftaranModelsList) {
        this.pendaftaranModelsList = pendaftaranModelsList;
    }
}

