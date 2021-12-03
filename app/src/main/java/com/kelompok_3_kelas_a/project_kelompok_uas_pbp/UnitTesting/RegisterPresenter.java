package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.UnitTesting;

import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;

public class RegisterPresenter {

    private RegisterView view;
    private RegisterService service;
    private RegisterCallback callback;
    private PenggunaModels pengguna;

    public RegisterPresenter(RegisterView view, RegisterService service)
    {
            this.view = view;
            this.service= service;
    }





}
