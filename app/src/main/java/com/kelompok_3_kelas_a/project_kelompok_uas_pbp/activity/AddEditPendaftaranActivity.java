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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.HalamanPendaftaran;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.HalamanUtama;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.PendaftaranApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PendaftaranModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PendaftaranResponse;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PendaftaranResponse2;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.preferences.userPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AddEditPendaftaranActivity extends AppCompatActivity {

    private static final String[] KELUHAN_LIST = new String[]{"Mata", "Perut", "Kepala", "Gigi", "Organ Dalam"};
    private static final String[] JENIS_KELAMIN_LIST = new String[]{"Laki-laki", "Perempuan"};

    private EditText et_namaPendaftar, et_tanggalLahir_Pendaftar, et_nomor_hp_pendaftar, et_tanggalPeriksa_pendaftar;
    private AutoCompleteTextView ed_keluhan_pendaftar, ed_jenisKelamin_pendaftar;
    private LinearLayout layoutLoading;
    private RequestQueue queue;
    private userPreferences userPreferences;
    private PenggunaModels penggunaModels;
    private long idUserPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_pendaftaran);
        setTitle(" ");
        queue = Volley.newRequestQueue(this);
        et_namaPendaftar = findViewById(R.id.et_namaPendaftar);
        et_tanggalLahir_Pendaftar = findViewById(R.id.et_tanggalLahir_Pendaftar);
        et_nomor_hp_pendaftar = findViewById(R.id.et_nomor_hp_pendaftar);
//        et_tanggalPeriksa_pendaftar = findViewById(R.id.et_tanggalPeriksa_pendaftar);
        ed_keluhan_pendaftar = findViewById(R.id.ed_keluhan_pendaftar);
        ed_jenisKelamin_pendaftar = findViewById(R.id.ed_jenisKelamin_pendaftar);
        layoutLoading = findViewById(R.id.layout_loading);

        ArrayAdapter<String> adapterKeluhan = new ArrayAdapter<>(this, R.layout.item_list_pendaftaran, KELUHAN_LIST);
        ed_keluhan_pendaftar.setAdapter(adapterKeluhan);

        ArrayAdapter<String> adapterJenisKelamin = new ArrayAdapter<>(this, R.layout.item_list_pendaftaran, JENIS_KELAMIN_LIST);
        ed_jenisKelamin_pendaftar.setAdapter(adapterJenisKelamin);

        userPreferences = new userPreferences(AddEditPendaftaranActivity.this);
        penggunaModels = userPreferences.getPenggunaModels();
        idUserPref = penggunaModels.getId();

        Button btnCancel = findViewById(R.id.btn_cancelPendaftaran);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddEditPendaftaranActivity.this, HalamanPendaftaran.class));
                finish();
            }
        });

        Button btnSave = findViewById(R.id.btn_savePendaftaran);
        TextView tvTitle = findViewById(R.id.tv_title_pendaftaran);
        int id = getIntent().getIntExtra("lemparId", -1);

        if (id == -1) {
            tvTitle.setText(R.string.tambah_pendaftaran);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createPendaftaran();
                }
            });
        } else {
            tvTitle.setText(R.string.edit_pendaftaran);
            int idDaftar = getIntent().getIntExtra("id", -1);
            getPendaftaranById(idDaftar);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updatePendaftaran(idDaftar);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddEditPendaftaranActivity.this, HalamanPendaftaran.class));
    }

    private void getPendaftaranById(int id){
        setLoading(true);
        StringRequest stringRequest = new StringRequest(GET, PendaftaranApi.GET_BY_ID_URL + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject object = jsonObject.getJSONObject("data");

                    et_namaPendaftar.setText(object.getString("namaPendaftar"));
                    et_tanggalLahir_Pendaftar.setText(object.getString("tanggalLahirPendaftar"));
                    et_nomor_hp_pendaftar.setText(object.getString("nomorHPPendaftar"));
                    ed_jenisKelamin_pendaftar.setText(object.getString("jenisKelaminPendaftar"), false);
                    ed_keluhan_pendaftar.setText(object.getString("keluhanPendaftar"), false);

                }catch (JSONException e){
                    e.printStackTrace();
                }
//
//                PendaftaranResponse2 pendaftaranResponse2 = gson.fromJson(response, PendaftaranResponse2.class);
//                PendaftaranModels pendaftaranModels = pendaftaranResponse2.getPendaftaranModelsList().get(0);
//
//                et_namaPendaftar.setText(pendaftaranModels.getNamaPendaftar());
//                et_tanggalLahir_Pendaftar.setText(pendaftaranModels.getTanggalLahirPendaftar());
//                et_nomor_hp_pendaftar.setText(pendaftaranModels.getNomorHPPendaftar());
////                et_tanggalPeriksa_pendaftar.setText(pendaftaranModels.getTanggalPeriksaPendaftar());
//
//                ed_keluhan_pendaftar.setText(pendaftaranModels.getKeluhanPendaftar(), false);
//                ed_jenisKelamin_pendaftar.setText(pendaftaranModels.getJenisKelaminPendaftar(), false);
//
                Toast.makeText(AddEditPendaftaranActivity.this, "Berhasil Ambil Data", Toast.LENGTH_SHORT).show();

                setLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(AddEditPendaftaranActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(AddEditPendaftaranActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void createPendaftaran(){
        setLoading(true);

        try{
            PendaftaranModels pendaftaranModels = new PendaftaranModels(
                    et_namaPendaftar.getText().toString(),
                    et_tanggalLahir_Pendaftar.getText().toString(),
                    et_nomor_hp_pendaftar.getText().toString(),
//                    et_tanggalPeriksa_pendaftar.getText().toString(),
                    ed_jenisKelamin_pendaftar.getText().toString(),
                    ed_keluhan_pendaftar.getText().toString());

            StringRequest stringRequest = new StringRequest(POST, PendaftaranApi.ADD_URL + idUserPref, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();

                    PendaftaranResponse pendaftaranResponse = gson.fromJson(response, PendaftaranResponse.class);
                    Toast.makeText(AddEditPendaftaranActivity.this, pendaftaranResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddEditPendaftaranActivity.this, HalamanPendaftaran.class));
//                    Intent returnIntent = new Intent();
//                    setResult(Activity.RESULT_OK, returnIntent);
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
                        Toast.makeText(AddEditPendaftaranActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditPendaftaranActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

                    String requestBody = gson.toJson(pendaftaranModels);
                    return requestBody.getBytes(StandardCharsets.UTF_8);
                }

                @Override public String getBodyContentType() {
                    return "application/json";
                }
            };
            queue.add(stringRequest);
        }
        catch (Exception e) {
            Toast.makeText(AddEditPendaftaranActivity.this, "Inputan Pendaftaran tidak boleh kosong", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddEditPendaftaranActivity.this, HalamanUtama.class));
            finish();
        }
    }

    private void updatePendaftaran(int id){
        setLoading(true);

        try{
            PendaftaranModels pendaftaranModels = new PendaftaranModels(
                    et_namaPendaftar.getText().toString(),
                    et_tanggalLahir_Pendaftar.getText().toString(),
                    et_nomor_hp_pendaftar.getText().toString(),
//                    et_tanggalPeriksa_pendaftar.getText().toString(),
                    ed_keluhan_pendaftar.getText().toString(),
                    ed_jenisKelamin_pendaftar.getText().toString());

            StringRequest stringRequest = new StringRequest(PUT, PendaftaranApi.UPDATE_URL + id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                /* Deserialiasai data dari response JSON dari API
                menjadi java object MahasiswaResponse menggunakan Gson */

                    PendaftaranResponse pendaftaranResponse = gson.fromJson(response, PendaftaranResponse.class);
                    Toast.makeText(AddEditPendaftaranActivity.this, pendaftaranResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AddEditPendaftaranActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditPendaftaranActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                /* Serialisasi data dari java object MahasiswaResponse
                menjadi JSON string menggunakan Gson */
                    String requestBody = gson.toJson(pendaftaranModels);
                    return requestBody.getBytes(StandardCharsets.UTF_8);
                }
                // Mendeklarasikan content type dari request body yang ditambahkan

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            }; // Menambahkan request ke request queue
            queue.add(stringRequest);
        }catch (Exception e) {
            Toast.makeText(AddEditPendaftaranActivity.this, "Inputan tidak boleh kosong", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddEditPendaftaranActivity.this, HalamanUtama.class));
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