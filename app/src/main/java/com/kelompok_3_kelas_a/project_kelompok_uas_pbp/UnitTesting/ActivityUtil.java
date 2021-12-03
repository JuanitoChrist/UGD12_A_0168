package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.UnitTesting;

import android.content.Context;
import android.content.Intent;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.email.RegisterActivity;



public class ActivityUtil {

    private Context context;
    public ActivityUtil(Context context) {
        this.context = context;
    }
    public void startRegisterActivity() {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }
}
