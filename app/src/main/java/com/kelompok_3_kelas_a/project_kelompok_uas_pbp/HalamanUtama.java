package com.kelompok_3_kelas_a.project_kelompok_uas_pbp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.BarChartActivityMain;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.ObatActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.PendaftaranActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.ProfilePenggunaActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.QR_Scanner_Main;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.hardware.QRScannerActivity;

public class HalamanUtama extends AppCompatActivity {

    private ImageButton btnObat, btnPendaftaran,
            btnJamKerja, btnScanQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);
        setTitle("Home Aplikasi");

        btnObat = findViewById(R.id.btnObat);
        btnPendaftaran = findViewById(R.id.btnDaftar);
        btnJamKerja = findViewById(R.id.btnJamKerja);
        btnScanQR = findViewById(R.id.btnScanQR);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation); /*masih error*/
        bottomNavigationView.setSelectedItemId(R.id.homeMenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        btnObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanUtama.this, HalamanObat.class));
                finish();
            }
        });
        btnPendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanUtama.this, HalamanPendaftaran.class));
                finish();
            }
        });
        btnJamKerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanUtama.this, BarChartActivityMain.class));
                finish();
            }
        });
        btnScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanUtama.this, QR_Scanner_Main.class));
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
//                            startActivity(new Intent(HalamanUtama.this, HalamanUtama.class));
//                            finish();
                            break;
                        case R.id.profileMenu:
                            startActivity(new Intent(HalamanUtama.this, ProfilePenggunaActivity.class));
                            finish();
                            break;
                    }
                    return true;
                }
            };
}