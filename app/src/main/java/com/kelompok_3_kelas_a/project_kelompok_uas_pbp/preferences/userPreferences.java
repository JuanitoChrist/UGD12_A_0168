package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;

public class userPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String KEY_ID = "id";

    public userPreferences(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(Long id){
        editor.putLong(KEY_ID, id);
        editor.commit();
    }

    public PenggunaModels getPenggunaModels(){
        Long id;

        id = sharedPreferences.getLong(KEY_ID, 0);
        return new PenggunaModels(id);
    }
}
