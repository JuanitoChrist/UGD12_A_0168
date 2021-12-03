package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.PUT;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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
    private static final String[] JENIS_KELAMIN_LIST = new String[]{"Laki-laki", "Perempuan"};

    private EditText etNama, etUmur, etEmail, etPassword;
    private ImageView ivGambar;
    private AutoCompleteTextView edJenisKelamin;
    private LinearLayout layoutLoading;
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
        edJenisKelamin = findViewById(R.id.ed_jenis_kelamin);

        ArrayAdapter<String> adapterJenisKelamin = new ArrayAdapter<>(this, R.layout.item_list_pengguna, JENIS_KELAMIN_LIST);
        edJenisKelamin.setAdapter(adapterJenisKelamin);
//        long id = getInte
//        getPenggunaById(id);
//        ivGambar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LayoutInflater layoutInflater = LayoutInflater.from(ProfilePenggunaActivity.this);
//                View selectMediaView = layoutInflater
//                        .inflate(R.layout.layout_select_media_pengguna, null);
//
//                final AlertDialog alertDialog = new AlertDialog
//                        .Builder(selectMediaView.getContext()).create();
//
//                Button btnKamera = selectMediaView.findViewById(R.id.btn_kamera);
//                Button btnGaleri = selectMediaView.findViewById(R.id.btn_galeri);
//
//                btnKamera.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        if (checkSelfPermission(Manifest.permission.CAMERA) ==
//                                PackageManager.PERMISSION_DENIED) {
//                            String[] permission = {Manifest.permission.CAMERA};
//                            requestPermissions(permission, PERMISSION_REQUEST_CAMERA);
//                        } else {
//                            // Membuka kamera
//                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            startActivityForResult(intent, CAMERA_REQUEST);
//                        }
//
//                        alertDialog.dismiss();
//                    }
//                });
//
//                btnGaleri.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        // Membuka galeri
//                        Intent intent = new Intent(Intent.ACTION_PICK,
//                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        startActivityForResult(intent, GALLERY_PICTURE);
//
//                        alertDialog.dismiss();
//                    }
//                });
//
//                alertDialog.setView(selectMediaView);
//                alertDialog.show();
//            }
//        });
//
//        Button btnCancel = findViewById(R.id.btn_cancel);
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//        Button btnSave = findViewById(R.id.btn_save);
//        TextView tvTitle = findViewById(R.id.tv_title);
//        long id = getIntent().getLongExtra("id", -1);

//        if (id == -1) {
////            tvTitle.setText(R.string.tambah_produk);
//
//            btnSave.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                      create
//                }
//            });
//        } else {
//        tvTitle.setText("Edit Profile");
////            getPenggunaById(id);
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                updateProfile(id);
//            }
//        });
////        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfilePenggunaActivity.this, HalamanUtama.class));
//        super.onBackPressed();
//        Intent returnIntent = new Intent();
//        setResult(RESULT_OK, returnIntent);
//        finish();
    }

//    private String bitmapToBase64(Bitmap bitmap) {
//        // TODO: Tambahkan fungsi untuk mengkonversi bitmap dengan output Base64 string hasil
//        //  konversi. Gunakan fungsi ini saat menambah atau mengedit data produk.
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//        byte[] byteArray = byteArrayOutputStream .toByteArray();
//        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
//
//        return encoded;
//    }

    private void getPenggunaById(long id) {
        setLoading(true);
        // Membuat request baru untuk mengambil data mahasiswa berdasarkan id
        JsonObjectRequest stringRequest = new JsonObjectRequest(GET, RegisterUserApi.GET_BY_ID_URL + id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Gson gson = new Gson();
// /* Deserialiasai data dari response JSON dari API
// menjadi java object MahasiswaResponse menggunakan Gson */
//                PenggunaResponse penggunaResponse = gson.fromJson(response, PenggunaResponse.class);
//                PenggunaModels penggunaModels = penggunaResponse.getPenggunaList().get(0);
                try {
                    JSONObject object = response.getJSONObject("user");
                    String nama = object.optString("name");
                    String email = object.optString("email");
                    int umur = object.optInt("umur");
                    String jenisKelammin = (object.optString("jenisKelamin"));

                    etNama.setText(nama);
                    etEmail.setText(email);
                    etUmur.setText(umur);
                    edJenisKelamin.setText(jenisKelammin, false);

                    Toast.makeText(ProfilePenggunaActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                    setLoading(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
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
        }) {
            // Menambahkan header pada request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }

    public void updateProfile(String nama, String email, int umur, String jenisKelamin){
//        RequestQueue queue = Volley
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

    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.INVISIBLE);
        }
    }
}