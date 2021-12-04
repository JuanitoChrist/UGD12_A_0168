package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.UnitTesting;

import com.android.volley.RequestQueue;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.email.RegisterActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;

public class RegisterPresenter {

    private RegisterView view;
    private RegisterService service;
    private RegisterCallback callback;
    private RegisterActivity activity;
    private RequestQueue queue;
    private PenggunaModels pengguna;

    public RegisterPresenter(RegisterView view, RegisterService service,RegisterActivity activity, RequestQueue queue)
    {
            this.view = view;
            this.service= service;
            this.activity = activity;
            this.queue = queue;
    }


    public void onRegisterClicked() {
        String regex = "[0-9]{1,2}";
        String regex1 =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
// Sumber pattern:
        https://developer.android.com/reference/java/util/regex/Pattern
        if (view.getNama().isEmpty()) {
            view.showNamaError("Nama tidak boleh kosong");
            return;
        } else if (view.getUmur().length() > 2) {
            view.showUmurError("Umur tidak lebih dari 2 digit");
            return;
        } else if(view.getUmur().isEmpty()){
            view.showUmurError("Umur tidak boleh kosong");
            return;
        } else if(!(view.getUmur().matches(regex))){
            view.showUmurError("Umur harus merupakan angka");
            return;
        } else if(view.getEmail().isEmpty()){
            view.showEmailError("Email tidak boleh kosong");
            return;
        } else if(!view.getEmail().matches(regex1)){
            view.showEmailError("Email harus berformat email");
            return;
        } else if(view.getJenisKelamin().isEmpty()){
            view.showJenisKelaminError("Jenis Kelamin tidak boleh kosong");
            return;
        } else if(view.getPassword().isEmpty()){
            view.showPasswordError("Password tidak boleh kosong");
            return;
        } else {
            service.register(view, pengguna, new RegisterCallback()  {
                @Override
                public void onSuccess(boolean value, PenggunaModels penggunaModels) {
                    view.startRegisterActivity();
                }

                @Override
                public void onError() {

                }
            },activity,queue);
            return;
        }
    }





}
