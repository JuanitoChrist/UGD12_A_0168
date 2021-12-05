package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.GET;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.HalamanObat;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.HalamanPendaftaran;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.adapters.TransaksiObatAdapter;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.PendaftaranApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.TransaksiObatApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.TransaksiObatResponse;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.preferences.userPreferences;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransaksiObatActivity extends AppCompatActivity {
    public static final int LAUNCH_ADD_ACTIVITY = 123;
    private SwipeRefreshLayout sr_transaksiObat;
    private TransaksiObatAdapter adapter;
    private SearchView sv_transaksiObat;
    private LinearLayout layoutLoading;
    private RequestQueue queue;
    private userPreferences userPreferences;
    private PenggunaModels penggunaModels;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Keranjang Pembelian Obat");
        setContentView(R.layout.activity_transaksi_obat);

        queue = Volley.newRequestQueue(this);
        layoutLoading = findViewById(R.id.layout_loading);
        sr_transaksiObat = findViewById(R.id.sr_transaksiObat);
        sv_transaksiObat = findViewById(R.id.sv_transaksiObat);
        userPreferences = new userPreferences(TransaksiObatActivity.this);

        penggunaModels = userPreferences.getPenggunaModels();
        id = penggunaModels.getId();

        sr_transaksiObat.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllProduk(id);
            }
        });

        sv_transaksiObat.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        RecyclerView rv_transaksiObat = findViewById(R.id.rv_transaksiObat);
        adapter = new TransaksiObatAdapter(new ArrayList<>(), this);
        rv_transaksiObat.setLayoutManager(new LinearLayoutManager(TransaksiObatActivity.this, LinearLayoutManager.VERTICAL, false));
        rv_transaksiObat.setAdapter(adapter);

        getAllProduk(id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_ADD_ACTIVITY && resultCode == Activity.RESULT_OK)
            getAllProduk(id);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TransaksiObatActivity.this, HalamanObat.class));
    }

    private void getAllProduk(long id) {
        sr_transaksiObat.setRefreshing(true);
        StringRequest stringRequest = new StringRequest(GET, TransaksiObatApi.GET_ALL_URL + id,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TransaksiObatResponse transaksiObatResponse = gson.fromJson(response, TransaksiObatResponse.class);

                adapter.setTransaksiObatModelsList(transaksiObatResponse.getTransaksiObatModelsList());
                adapter.getFilter().filter(sv_transaksiObat.getQuery());

                Toast.makeText(TransaksiObatActivity.this, "sini", Toast.LENGTH_SHORT).show();
                Toast.makeText(TransaksiObatActivity.this, transaksiObatResponse.getMessage(),
                        Toast.LENGTH_SHORT).show();

                sr_transaksiObat.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sr_transaksiObat.setRefreshing(false);
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(TransaksiObatActivity.this, "error", Toast.LENGTH_SHORT).show();
                    Toast.makeText(TransaksiObatActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(TransaksiObatActivity.this, "error 2", Toast.LENGTH_SHORT).show();
                    Toast.makeText(TransaksiObatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

    public void deleteTransaksiObat(long id) {
        setLoading(true);

        StringRequest stringRequest = new StringRequest(DELETE, PendaftaranApi.DELETE_URL + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                TransaksiObatResponse transaksiObatResponse = gson.fromJson(response, TransaksiObatResponse.class);
                setLoading(false);
                Toast.makeText(TransaksiObatActivity.this, transaksiObatResponse.getMessage(), Toast.LENGTH_SHORT).show();
                getAllProduk(id);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(TransaksiObatActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(TransaksiObatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

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