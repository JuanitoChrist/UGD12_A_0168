package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models;

public class ObatModels {

    private String namaObat, gambarObat;
    private Integer stokObat, hargaObat, idObat;

    public ObatModels( Integer idObat, String namaObat, String gambarObat, Integer stokObat, Integer hargaObat) {
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.gambarObat = gambarObat;
        this.stokObat = stokObat;
        this.hargaObat = hargaObat;
    }

    public Integer getIdObat() {
        return idObat;
    }

    public void setIdObat(Integer idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getGambarObat() {
        return gambarObat;
    }

    public void setGambarObat(String gambarObat) {
        this.gambarObat = gambarObat;
    }

    public Integer getStokObat() {
        return stokObat;
    }

    public void setStokObat(Integer stokObat) {
        this.stokObat = stokObat;
    }

    public Integer getHargaObat() {
        return hargaObat;
    }

    public void setHargaObat(Integer hargaObat) {
        this.hargaObat = hargaObat;
    }
}
