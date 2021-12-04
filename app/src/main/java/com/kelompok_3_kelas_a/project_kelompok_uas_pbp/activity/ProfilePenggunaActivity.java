package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.PUT;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.HalamanUtama;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.ApiInterface;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.PendaftaranApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.RegisterUserApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaResponse;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.preferences.userPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ProfilePenggunaActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CAMERA = 100;
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_PICTURE = 1;
    public static final String SHARE_PREFS = "SharedPrefUser";
    public static final String SAVE_ID = "id";
    private static final String[] JENIS_KELAMIN_LIST = new String[]{"Laki-laki", "Perempuan"};

    private long id;
    private EditText etNama, etUmur, etEmail, etPassword, et_jenis_kelamin;
    private Button btnUpdate, btnCancel;
    private AutoCompleteTextView edJenisKelamin;
    private LinearLayout layoutLoading;
    private userPreferences userPreferences;
    private PenggunaModels penggunaModels;
//    private ApiInterface apiInterface;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pengguna);
//        apiInterface = ApiClient.getClient().create(ApiInterface .class);
        queue = Volley.newRequestQueue(this);
        etNama = findViewById(R.id.et_nama);
        etUmur = findViewById(R.id.et_umur);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        et_jenis_kelamin = findViewById(R.id.et_jenis_kelamin);
        btnUpdate = findViewById(R.id.btn_update);
        btnCancel = findViewById(R.id.btn_cancel);

        userPreferences = new userPreferences(ProfilePenggunaActivity.this);
        penggunaModels = userPreferences.getPenggunaModels();
        id = penggunaModels.getId();

        getPenggunaById(id);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                String umur = etUmur.getText().toString();
                String email = etEmail.getText().toString();
                String jenisKelamin = et_jenis_kelamin.getText().toString();
                updateProfile(nama, umur, email, jenisKelamin, id);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilePenggunaActivity.this, HalamanUtama.class));
            }
        });


//        ArrayAdapter<String> adapterJenisKelamin = new ArrayAdapter<>(this, R.layout.item_list_pengguna, JENIS_KELAMIN_LIST);
//        et.setAdapter(adapterJenisKelamin);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfilePenggunaActivity.this, HalamanUtama.class));
    }

    private void getPenggunaById(long id) {
//        setLoading(true);
        // Membuat request baru untuk mengambil data mahasiswa berdasarkan id
        StringRequest stringRequest = new StringRequest(GET, RegisterUserApi.GET_BY_ID_URL + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
 /* Deserialiasai data dari response JSON dari API
 menjadi java object MahasiswaResponse menggunakan Gson */
                PenggunaResponse penggunaResponse = gson.fromJson(response, PenggunaResponse.class);
                PenggunaModels penggunaModels = penggunaResponse.getPenggunaList().get(0);
                etNama.setText(penggunaModels.getNama());
                etEmail.setText(penggunaModels.getEmail());
                etUmur.setText(penggunaModels.getUmur());
                et_jenis_kelamin.setText(penggunaModels.getJenisKelamin());
//                try {
//                    JSONObject object = response.getJSONObject("user");
////                    String nama = object.optString("name");
////                    String email = object.optString("email");
////                    String umur = object.optString("umur");
////                    String jenisKelamin = (object.optString("jenisKelamin"));
//
//                    etNama.setText(object.optString("name"));
//                    etEmail.setText(object.optString("email"));
//                    etUmur.setText(object.optString("umur"));
//                    etEmail.setText(object.optString("email"));
//                    et_jenis_kelamin.setText(object.optString("jenisKelamin"));
//
//                    Toast.makeText(ProfilePenggunaActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
////                    setLoading(false);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(ProfilePenggunaActivity.this, errors.getString("message"),
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ProfilePenggunaActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) ;
//        {
//            // Menambahkan header pada request
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Accept", "application/json");
//                return headers;
//            }
//        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }

    public void updateProfile(String nama, String umur, String email, String jenisKelamin, long id){
        RequestQueue queue = Volley.newRequestQueue(ProfilePenggunaActivity.this);

        StringRequest stringRequest = new StringRequest(PUT, RegisterUserApi.UPDATE_URL + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    Toast.makeText(ProfilePenggunaActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfilePenggunaActivity.this, HalamanUtama.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Toast.makeText(ProfilePenggunaActivity.this, email, Toast.LENGTH_SHORT).show();
                if (networkResponse != null && networkResponse.data != null)
                {
                    String jsonError = new String(networkResponse.data);
                    Toast.makeText(ProfilePenggunaActivity.this, jsonError, Toast.LENGTH_SHORT).show();
                }
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", nama);
                params.put("umur", umur);
                params.put("email", email);
                params.put("jenisKelamin", jenisKelamin);

                return params;
            }
        };

        queue.add(stringRequest);
    }

//    private void updateProfile(long id){
//        setLoading(true);
//
//        PenggunaModels penggunaModels = new PenggunaModels(
//                etNama.getText().toString(),
//                Integer.parseInt(etUmur.getText().toString()),
//                edJenisKelamin.getText().toString(),
//                etPassword.getText().toString(),
////                bitmapToBase64(((BitmapDrawable) ivGambar.getDrawable()).getBitmap()),
//                etEmail.getText().toString()
//        );
//        //updateService
//        StringRequest stringRequest = new StringRequest(PUT, RegisterUserApi.UPDATE_URL + id, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                PenggunaResponse penggunaResponse = gson.fromJson(response, PenggunaResponse.class);
//                Toast.makeText(ProfilePenggunaActivity.this, penggunaResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                Intent returnIntent = new Intent();
//                setResult(Activity.RESULT_OK, returnIntent);
//                finish();
//                setLoading(false);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
//                try {
//                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
//                    JSONObject errors = new JSONObject(responseBody);
//                    Toast.makeText(ProfilePenggunaActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    Toast.makeText(ProfilePenggunaActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Accept", "application/json");
//                return headers;
//            }
//
//            @Override
//            public byte[] getBody() throws AuthFailureError {
//                Gson gson = new Gson();
//
//                String requestBody = gson.toJson(penggunaModels);
//                return requestBody.getBytes(StandardCharsets.UTF_8);
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "application/json";
//            }
//        };
//
//        queue.add(stringRequest);
//    }

//    private void setLoading(boolean isLoading) {
//        if (isLoading) {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            layoutLoading.setVisibility(View.VISIBLE);
//        } else {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            layoutLoading.setVisibility(View.INVISIBLE);
//        }
//    }
}