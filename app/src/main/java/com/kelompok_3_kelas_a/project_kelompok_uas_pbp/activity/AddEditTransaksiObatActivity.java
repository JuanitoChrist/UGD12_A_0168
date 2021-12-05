package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;
import static com.android.volley.Request.Method.PUT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.HalamanUtama;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.TransaksiObatApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.ObatList;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.ObatModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.TransaksiObatModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.TransaksiObatResponse;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.preferences.userPreferences;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddEditTransaksiObatActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CAMERA = 100;
    private EditText et_namaPembeliTransaksi, et_nomorHP_pembeli,
            et_alamatPembeli, et_umurPembeli, et_jumlahBeliTransaksi;

    private TextView tv_hargaObatTransaksi, tv_namaObatTransaksi;
    private LinearLayout layoutLoading;
    private RequestQueue queue;
    private ImageView iv_gambarTransaksiObat;
    private Integer tampungIdObat;
    ArrayList<ObatModels> obatModelsArrayList;
    private ObatModels obatDipilih;
    private userPreferences userPreferences;
    private PenggunaModels penggunaModels;
    private long idUserPref;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_transaksi_obat);
        setTitle(" ");

        obatModelsArrayList = new ObatList().ObatModels;
        tampungIdObat = getIntent().getIntExtra("id", 0);

        queue = Volley.newRequestQueue(this);
        tv_namaObatTransaksi = findViewById(R.id.tv_namaObatTransaksi);
        tv_hargaObatTransaksi = findViewById(R.id.tv_hargaObatTransaksi);

        et_namaPembeliTransaksi = findViewById(R.id.et_namaPembeliTransaksi);
        et_nomorHP_pembeli = findViewById(R.id.et_nomorHP_pembeli);
        et_alamatPembeli = findViewById(R.id.et_alamatPembeli);
        et_umurPembeli = findViewById(R.id.et_umurPembeli);
        et_jumlahBeliTransaksi = findViewById(R.id.et_jumlahBeliTransaksi);

        iv_gambarTransaksiObat = findViewById(R.id.iv_gambarTransaksiObat);

        layoutLoading = findViewById(R.id.layout_loading);

        userPreferences = new userPreferences(AddEditTransaksiObatActivity.this);
        penggunaModels = userPreferences.getPenggunaModels();
        idUserPref = penggunaModels.getId();

        setObatDipilih();
        Glide.with(AddEditTransaksiObatActivity.this)
                .load(obatDipilih.getGambarObat())
                .centerCrop()
                .into(iv_gambarTransaksiObat);

        tv_namaObatTransaksi.setText(obatDipilih.getNamaObat());
        tv_hargaObatTransaksi.setText(obatDipilih.getHargaObat().toString());

        Button btnCancel = findViewById(R.id.btn_cancelTransaksiObat);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnSave = findViewById(R.id.btn_saveTransaksiObat);
        TextView tvTitle = findViewById(R.id.tv_titleTransaksiObat);
        long id = getIntent().getLongExtra("id", -1);

        if (id == -1) {
            tvTitle.setText(R.string.tambah_transaksiObat);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createTransaksiObat();
                }
            });
        } else {
            tvTitle.setText(R.string.edit_transaksiObat);
            getTransaksiObatById(id);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateTransakiObat(id);
                }
            });
        }
    }

    private void setObatDipilih(){
        for(int i=0; i<obatModelsArrayList.size(); i++){
            if(obatModelsArrayList.get(i).getIdObat()==tampungIdObat){
                obatDipilih = obatModelsArrayList.get(i);
            }
        }
    }

    private void getTransaksiObatById(long id){
        setLoading(true);

        StringRequest stringRequest = new StringRequest(GET, TransaksiObatApi.GET_BY_ID_URL + idUserPref, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                TransaksiObatResponse transaksiObatResponse = gson.fromJson(response, TransaksiObatResponse.class);
                TransaksiObatModels transaksiObatModels = transaksiObatResponse.getTransaksiObatModelsList().get(0);

                et_namaPembeliTransaksi.setText(transaksiObatModels.getNamaPembeli());
                et_nomorHP_pembeli.setText(transaksiObatModels.getNomorHpPembeli());
                et_alamatPembeli.setText(transaksiObatModels.getAlamatPembeli());
                et_umurPembeli.setText(transaksiObatModels.getUmurPembeli());
                et_jumlahBeliTransaksi.setText(transaksiObatModels.getJumlahBeli().toString());

                Toast.makeText(AddEditTransaksiObatActivity.this, transaksiObatResponse.getMessage(), Toast.LENGTH_SHORT).show();

                setLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(AddEditTransaksiObatActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(AddEditTransaksiObatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void createTransaksiObat(){
        setLoading(true);
        total = Double.parseDouble(tv_hargaObatTransaksi.getText().toString()) * Double.parseDouble(et_jumlahBeliTransaksi.getText().toString());

        try{
            TransaksiObatModels transaksiObatModels = new TransaksiObatModels(
                    et_namaPembeliTransaksi.getText().toString(),
                    et_nomorHP_pembeli.getText().toString(),
                    et_alamatPembeli.getText().toString(),
                    et_umurPembeli.getText().toString(),
                    Integer.parseInt(et_jumlahBeliTransaksi.getText().toString()),
                    obatDipilih.getIdObat(), total);

            StringRequest stringRequest = new StringRequest(POST, TransaksiObatApi.ADD_URL + idUserPref, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();

                    TransaksiObatResponse transaksiObatResponse = gson.fromJson(response, TransaksiObatResponse.class);
                    Toast.makeText(AddEditTransaksiObatActivity.this, transaksiObatResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                    setLoading(false);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    setLoading(false);
                    try {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        JSONObject errors = new JSONObject(responseBody);
                        Toast.makeText(AddEditTransaksiObatActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditTransaksiObatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }) {
                @Override
                public
                Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Accept", "application/json");
                    return headers;
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    Gson gson = new Gson();

                    String requestBody = gson.toJson(transaksiObatModels);
                    return requestBody.getBytes(StandardCharsets.UTF_8);
                }
                @Override public String getBodyContentType() {
                    return "application/json";
                }
            };
            queue.add(stringRequest);
        }catch (Exception e) {
            Toast.makeText(AddEditTransaksiObatActivity.this, "Inputan Transaksi Obat tidak boleh kosong", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddEditTransaksiObatActivity.this, HalamanUtama.class));
            finish();
        }
    }

    private void updateTransakiObat(long id){
        setLoading(true);

        try{
            TransaksiObatModels transaksiObatModels = new TransaksiObatModels(
                    et_namaPembeliTransaksi.getText().toString(),
                    et_nomorHP_pembeli.getText().toString(),
                    et_alamatPembeli.getText().toString(),
                    et_umurPembeli.getText().toString(),
                    Integer.parseInt(et_jumlahBeliTransaksi.getText().toString()),
                    obatDipilih.getIdObat());

            StringRequest stringRequest = new StringRequest(PUT, TransaksiObatApi.UPDATE_URL + id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    TransaksiObatResponse transaksiObatResponse = gson.fromJson(response, TransaksiObatResponse.class);
                    Toast.makeText(AddEditTransaksiObatActivity.this, transaksiObatResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();

                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                    setLoading(false);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    setLoading(false);
                    try {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        JSONObject errors = new JSONObject(responseBody);
                        Toast.makeText(AddEditTransaksiObatActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditTransaksiObatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Accept", "application/json");

                    return headers;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    Gson gson = new Gson();
                    String requestBody = gson.toJson(transaksiObatModels);
                    return requestBody.getBytes(StandardCharsets.UTF_8);
                }
                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            }; // Menambahkan request ke request queue
            queue.add(stringRequest);
        }catch (Exception e) {
            Toast.makeText(AddEditTransaksiObatActivity.this, "Inputan Transaksi Obat tidak boleh kosong", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddEditTransaksiObatActivity.this, HalamanUtama.class));
            finish();
        }
    }

    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.INVISIBLE);
        }
    }
}