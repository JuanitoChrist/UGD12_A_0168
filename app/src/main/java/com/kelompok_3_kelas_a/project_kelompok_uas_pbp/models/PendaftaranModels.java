package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models;

public class PendaftaranModels {

    private Long id;
    private String namaPendaftar, tanggalLahirPendaftar,
            nomorHPPendaftar, tanggalPeriksaPendaftar, jenisKelaminPendaftar,
            keluhanPendaftar;

    public PendaftaranModels(String namaPendaftar, String tanggalLahirPendaftar, String nomorHPPendaftar, String tanggalPeriksaPendaftar,
                             String jenisKelaminPendaftar, String keluhanPendaftar) {
        this.namaPendaftar = namaPendaftar;
        this.tanggalLahirPendaftar = tanggalLahirPendaftar;
        this.nomorHPPendaftar = nomorHPPendaftar;
        this.tanggalPeriksaPendaftar = tanggalPeriksaPendaftar;
        this.jenisKelaminPendaftar = jenisKelaminPendaftar;
        this.keluhanPendaftar = keluhanPendaftar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPendaftar() {
        return namaPendaftar;
    }

    public void setNamaPendaftar(String namaPendaftar) {
        this.namaPendaftar = namaPendaftar;
    }

    public String getTanggalLahirPendaftar() {
        return tanggalLahirPendaftar;
    }

    public void setTanggalLahirPendaftar(String tanggalLahirPendaftar) {
        this.tanggalLahirPendaftar = tanggalLahirPendaftar;
    }

    public String getNomorHPPendaftar() {
        return nomorHPPendaftar;
    }

    public void setNomorHPPendaftar(String nomorHPPendaftar) {
        this.nomorHPPendaftar = nomorHPPendaftar;
    }

    public String getTanggalPeriksaPendaftar() {
        return tanggalPeriksaPendaftar;
    }

    public void setTanggalPeriksaPendaftar(String tanggalPeriksaPendaftar) {
        this.tanggalPeriksaPendaftar = tanggalPeriksaPendaftar;
    }

    public String getJenisKelaminPendaftar() {
        return jenisKelaminPendaftar;
    }

    public void setJenisKelaminPendaftar(String jenisKelaminPendaftar) {
        this.jenisKelaminPendaftar = jenisKelaminPendaftar;
    }

    public String getKeluhanPendaftar() {
        return keluhanPendaftar;
    }

    public void setKeluhanPendaftar(String keluhanPendaftar) {
        this.keluhanPendaftar = keluhanPendaftar;
    }
}
