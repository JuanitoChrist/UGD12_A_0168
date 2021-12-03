package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.UnitTesting;

import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;

public interface RegisterCallback {

    void onSuccess(boolean value, PenggunaModels penggunaModels);
    void onError();

}
