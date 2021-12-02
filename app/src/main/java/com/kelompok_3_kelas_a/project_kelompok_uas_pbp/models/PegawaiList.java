package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models;

import java.util.Arrays;
import java.util.List;

public class PegawaiList {

    public String nama_pegawai;
    public String nomor_pokok;
    public String jabatan;
    public String alamat;
    public String gaji;
    public String tahun_masuk;

    public PegawaiList(String nama_pegawai, String nomor_pokok, String jabatan,
                   String alamat, String gaji, String tahun_masuk) {
        this.nama_pegawai = nama_pegawai;
        this.nomor_pokok = nomor_pokok;
        this.jabatan = jabatan;
        this.alamat = alamat;
        this.gaji = gaji;
        this.tahun_masuk = tahun_masuk;
    }
    public static List<PegawaiList> generateListPegawai() {
        return Arrays.asList(
                new PegawaiList("80500", "Senin", "28 Agustus", "Yogyakarta", "Pria", "2013"),
                new PegawaiList("95000", "Selasa", "29 Agustus", "Kaliurang", "Wanita", "2014"),
                new PegawaiList("105000", "Rabu", "30 Agustus",
                        "Jawa Tengah", "Pria", "2015"),
                new PegawaiList("110500", "Kamis", "31 Agustus", "Kalimantan Barat", "Wanita", "2016"),
                new PegawaiList("128000", "Jumat", "32 Agustus", "Yogyakarta", "Wanita", "2017"),
                new PegawaiList("143000", "Sabtu", "33 Agustus", "Sukoharjo Solo", "Wanita", "2018"),
                new PegawaiList("170600", "Minggu", "34 Agustus", "Malang", "Pria", "2019")
        );
    }
    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getNomor_pokok() {
        return nomor_pokok;
    }

    public void setNomor_pokok(String nomor_pokok) {
        this.nomor_pokok = nomor_pokok;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getTahun_masuk() {
        return tahun_masuk;
    }

    public void setTahun_masuk(String tahun_masuk) {
        this.tahun_masuk = tahun_masuk;
    }

}
