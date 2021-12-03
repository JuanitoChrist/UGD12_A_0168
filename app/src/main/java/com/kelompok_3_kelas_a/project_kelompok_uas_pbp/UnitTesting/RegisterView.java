package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.UnitTesting;

public interface RegisterView {

        String getNama();
        void showNamaError(String message);
        String getUmur();
        void showUmurError(String message);
        String getEmail();
        void showEmailError(String message);
        String getJenisKelamin();
        void showJenisKelaminError(String message);
        String getPassword();
        void showPasswordError(String message);
        void startRegisterActivity();



}
