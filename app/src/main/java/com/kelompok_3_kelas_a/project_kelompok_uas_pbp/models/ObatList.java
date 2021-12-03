package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models;

import java.util.ArrayList;

public class ObatList {

    public ArrayList<ObatModels> ObatModels;
    public ObatList(){
        ObatModels = new ArrayList();
        ObatModels.add(Panadol);
        ObatModels.add(ImBoost);
        ObatModels.add(BearBrand);
        ObatModels.add(CurcumaPlus);
        ObatModels.add(OBHCombi);
        ObatModels.add(Stimuno);
        ObatModels.add(Diapet);
        ObatModels.add(Vegeta);
    }

    public static final ObatModels Panadol = new ObatModels(1, "Panadol", "https://hdmall.id/system/image_attachments/images/000/008/719/medium/panadol-biru-tab-10-s-1.jpg",
            100, 10000);
    public static final ObatModels ImBoost = new ObatModels(2, "IMBoost", "https://images.k24klik.com/product/large/apotek_online_k24klik_20201204100519359225_IMBOOST-FORCE.jpg",
            50, 50000);
    public static final ObatModels BearBrand = new ObatModels(3,"Bear Brand", "https://cf.shopee.co.id/file/b3b3ccd7251b56b0fcb8955aa5206859",
            1000, 15000);
    public static final ObatModels CurcumaPlus = new ObatModels(4,"Curcuma Plus", "https://static.bmdstatic.com/pk/product/medium/5f56ff0cc44b2.jpg",
            500, 50000);
    public static final ObatModels OBHCombi = new ObatModels(5,"OBH Combi", "https://hdmall.id/system/image_attachments/images/000/009/536/medium/obh-combi-dewasa-batuk-flu-jahe-60ml-1.jpg",
            100, 25000);
    public static final ObatModels Stimuno = new ObatModels(6,"Stimuno", "https://assets.babyzania.com/image/cache/catalog/1/stimuno-800x800.jpg",
            300, 17500);
    public static final ObatModels Diapet = new ObatModels(7,"Diapet", "https://lifepack.id/wp-content/uploads/2021/02/18.jpg",
            300, 25000);
    public static final ObatModels Vegeta = new ObatModels(8, "Vegeta", "https://hdmall.id/system/image_attachments/images/000/012/167/medium/vegeta-herbal-anggur-sach-1.jpg",
            1000, 5000);
}
