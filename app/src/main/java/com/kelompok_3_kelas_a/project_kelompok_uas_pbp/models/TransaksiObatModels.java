package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models;

public class TransaksiObatModels {

    private Long idTransaksiObat;
    private ObatModels obatModels;
    private String namaPembeli, nomorHpPembeli, alamatPembeli, umurPembeli;
    private Integer jumlahBeli, idObat;
    private double totalBayarObat;

//    public TransaksiObatModels(String namaPembeli, String nomorHpPembeli, String alamatPembeli,
//                               String umurPembeli, Integer jumlahBeli) {
//        this.namaPembeli = namaPembeli;
//        this.nomorHpPembeli = nomorHpPembeli;
//        this.alamatPembeli = alamatPembeli;
//        this.umurPembeli = umurPembeli;
//        this.jumlahBeli = jumlahBeli;
//    }

    public TransaksiObatModels(String namaPembeli, String nomorHpPembeli, String alamatPembeli,
                               String umurPembeli, Integer jumlahBeli, Integer idObat) {
        this.namaPembeli = namaPembeli;
        this.nomorHpPembeli = nomorHpPembeli;
        this.alamatPembeli = alamatPembeli;
        this.umurPembeli = umurPembeli;
        this.jumlahBeli = jumlahBeli;
        this.idObat = idObat;
    }

    public TransaksiObatModels(String namaPembeli, String nomorHpPembeli, String alamatPembeli,
                               String umurPembeli, Integer jumlahBeli, Integer idObat, double totalBayarObat) {
        this.namaPembeli = namaPembeli;
        this.nomorHpPembeli = nomorHpPembeli;
        this.alamatPembeli = alamatPembeli;
        this.umurPembeli = umurPembeli;
        this.jumlahBeli = jumlahBeli;
        this.idObat = idObat;
        this.totalBayarObat = totalBayarObat;
    }

    public Long getIdTransaksiObat() {
        return idTransaksiObat;
    }

    public void setIdTransaksiObat(Long idTransaksiObat) {
        this.idTransaksiObat = idTransaksiObat;
    }

    public ObatModels getObatModels() {
        return obatModels;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNomorHpPembeli() {
        return nomorHpPembeli;
    }

    public void setNomorHpPembeli(String nomorHpPembeli) {
        this.nomorHpPembeli = nomorHpPembeli;
    }

    public String getAlamatPembeli() {
        return alamatPembeli;
    }

    public void setAlamatPembeli(String alamatPembeli) {
        this.alamatPembeli = alamatPembeli;
    }

    public String getUmurPembeli() {
        return umurPembeli;
    }

    public void setUmurPembeli(String umurPembeli) {
        this.umurPembeli = umurPembeli;
    }

    public Integer getJumlahBeli() {
        return jumlahBeli;
    }

    public void setJumlahBeli(Integer jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    public double getTotalBayarObat() {
        return totalBayarObat = this.jumlahBeli * this.obatModels.getHargaObat();
    }

    public void setTotalBayarObat(double totalBayarObat) {
        this.totalBayarObat = totalBayarObat;
    }

    public void setObatModels(ObatModels obatModels) {
        this.obatModels = obatModels;
    }
}
