package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models;

import com.google.gson.annotations.SerializedName;

public class PenggunaModels {

    private Long id;
    private String nama;
    private int umur;
    private String email;

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;
//    private String gambar;

    public PenggunaModels(String nama, int umur, String email, String jenisKelamin) {
        this.nama = nama;
        this.umur = umur;
        this.email = email;
        this.jenisKelamin = jenisKelamin;
//        this.gambar = gambar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

//    public String getGambar() {
//        return gambar;
//    }
//
//    public void setGambar(String gambar) {
//        this.gambar = gambar;
//    }
}
