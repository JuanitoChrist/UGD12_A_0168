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
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.PendaftaranActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.ProfilePenggunaActivity;

public class HalamanPendaftaran extends AppCompatActivity {

    private ImageButton btnDaftar_HalamanPendaftaran, btnListDaftar_HalamanPendaftaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        setTitle(" ");

        btnDaftar_HalamanPendaftaran = findViewById(R.id.btnDaftar_HalamanPendaftaran);
        btnListDaftar_HalamanPendaftaran = findViewById(R.id.btnListDaftar_HalamanPendaftaran);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation); /*masih error*/
        bottomNavigationView.setSelectedItemId(R.id.homeMenu); /*error*/
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        btnDaftar_HalamanPendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanPendaftaran.this, AddEditPendaftaranActivity.class));
                finish();
            }
        });
        btnListDaftar_HalamanPendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanPendaftaran.this, PendaftaranActivity.class));
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
                            startActivity(new Intent(HalamanPendaftaran.this, HalamanUtama.class));
                            finish();
                            break;
                        case R.id.profileMenu:
                            startActivity(new Intent(HalamanPendaftaran.this, ProfilePenggunaActivity.class));
                            finish();
                            break;
                    }
                    return true;
                }
            };
}