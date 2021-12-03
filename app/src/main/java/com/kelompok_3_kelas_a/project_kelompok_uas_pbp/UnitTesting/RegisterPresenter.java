//package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.UnitTesting;
//
//import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;
//
//public class RegisterPresenter {
//
//    private RegisterView view;
//    private RegisterService service;
//    private RegisterCallback callback;
//    private PenggunaModels pengguna;
//
//    public RegisterPresenter(RegisterView view, RegisterService service)
//    {
//            this.view = view;
//            this.service= service;
//    }
//
//
//    public void onRegisterClicked() {
//        String regex = "[1-9]+";
//        String regex1 = "[a-zA-Z]";
//// Sumber pattern:
//        https://developer.android.com/reference/java/util/regex/Pattern
//        if (view.getNama().isEmpty()) {
//            view.showNamaError("Nama tidak boleh kosong");
//            return;
//        } else if (view.getUmur().get) {
//            view.showLastNameError("Nama Belakang tidak boleh
//                    kosong");
//            return;
//        } else if(view.getSemester().isEmpty()){
//            view.showSemesterError("Semester tidak boleh kosong");
//            return;
//        } else if(!(view.getSemester().matches(regex))){
//            view.showSemesterError("Format Semester harus berupa
//                    angka 1 sampai 9");
//            return;
//        } else if(view.getTanggalLahir().isEmpty()){
//            view.showTanggalLahirError("Tanggal Lahir tidak boleh
//                    kosong");
//            return;
//        } else if(view.getTanggalLahir().length() < 6){
//            view.showTanggalLahirError("Tanggal Lahir tidak boleh
//                    kurang dari 6 digit");
//            return;
//        } else if(view.getTanggalLahir().length() > 6){
//            view.showTanggalLahirError("Tanggal Lahir tidak boleh
//                    lebih dari 6 digit");
//            return;
//        } else {
//            service.profil(view, profil, new ProfilCallback() {
//                @Override
//                public void onSuccess(boolean value, Profil profil)
//                {
//                    view.startMainProfil();
//                }
//                @Override
//                public void onError() {
//                }
//            });
//            return;
//        }
//    }
//
//
//
//
//
//}
