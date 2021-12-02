package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.GET;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.adapters.ObatAdapter;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.adapters.PendaftaranAdapter;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.PendaftaranApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.databinding.ItemObatTampilBinding;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.ObatList;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.ObatModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PendaftaranResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObatActivity extends AppCompatActivity {
    public static final int LAUNCH_ADD_ACTIVITY = 123;

    private SwipeRefreshLayout sr_obat;
    private ObatAdapter adapter;
    private SearchView sv_obat;
    private LinearLayout layoutLoading;
    ArrayList<ObatModels> obatModelsArrayList;

    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        obatModelsArrayList = new ObatList().ObatModels;

        setTitle("Daftar Obat");
        setContentView(R.layout.activity_pendaftaran);

        layoutLoading = findViewById(R.id.layout_loading);
        sr_obat = findViewById(R.id.sr_obat);
        sv_obat = findViewById(R.id.sv_obat);

        sv_obat.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        RecyclerView rv_obat = findViewById(R.id.rv_obat);

        rv_obat.setLayoutManager(new LinearLayoutManager(this.getBaseContext(),LinearLayoutManager.VERTICAL,false));
        rv_obat.setAdapter(new ObatAdapter(obatModelsArrayList));
    }

    // Fungsi ini digunakan menampilkan layout loading
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.GONE);
        }
    }

}
