package com.kelompok_3_kelas_a.project_kelompok_uas_pbp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.AddEditPendaftaranActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.ObatActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.PendaftaranActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.ProfilePenggunaActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.TransaksiObatActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.adapters.ObatAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HalamanObat extends AppCompatActivity {

    private ImageButton btnBeliObat_HalamanObat, btnDaftarBeliObat_HalamanObat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat);
        setTitle(" ");

        btnBeliObat_HalamanObat = findViewById(R.id.btnBeliObat_HalamanObat);
        btnDaftarBeliObat_HalamanObat = findViewById(R.id.btnDaftarBeliObat_HalamanObat);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation); /*masih error*/
        bottomNavigationView.setSelectedItemId(R.id.homeMenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        btnBeliObat_HalamanObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanObat.this, ObatAdapter.class));
                finish();
            }
        });
        btnDaftarBeliObat_HalamanObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanObat.this, TransaksiObatActivity.class));
                finish();
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item){
                    switch(item.getItemId()){
                        case R.id.homeMenu:
                            startActivity(new Intent(HalamanObat.this, HalamanUtama.class));
                            finish();
                            break;
                        case R.id.profileMenu:
                            startActivity(new Intent(HalamanObat.this, ProfilePenggunaActivity.class));
                            finish();
                            break;
                    }
                    return true;
                }
            };
}