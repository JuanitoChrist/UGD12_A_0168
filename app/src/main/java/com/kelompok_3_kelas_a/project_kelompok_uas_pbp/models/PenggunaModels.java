package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models;

import com.google.gson.annotations.SerializedName;

public class PenggunaModels {

    private Long id;
    @SerializedName("name")
    private String nama;
    private String umur;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("jenisKelamin")
    private String jenisKelamin;
//    private String gambar;

    public PenggunaModels(String nama, String umur, String email, String password, String jenisKelamin) {
        this.nama = nama;
        this.setUmur(umur);
        this.email = email;
        this.jenisKelamin = jenisKelamin;
        this.setPassword(password);
//        this.gambar = gambar;
    }

    public PenggunaModels(String email, String password){
        this.email = email;
        this.password = password;
    }

    public PenggunaModels(long id){
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

//    public String getGambar() {
//        return gambar;
//    }
//
//    public void setGambar(String gambar) {
//        this.gambar = gambar;
//    }
}
