package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.email;

import static android.widget.Toast.LENGTH_SHORT;
import static com.android.volley.Request.Method.POST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.UnitTesting.ActivityUtil;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.UnitTesting.RegisterView;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.RegisterUserApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    private static final String[] JENIS_KELAMIN_LIST = new String[]{"Laki-laki", "Perempuan"};

    private EditText etNama, etUmur, etEmail, etPassword, et_jenis_kelamin;
    private Button btnSave;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        queue = Volley.newRequestQueue(this);
        etNama = findViewById(R.id.et_nama);
        etUmur = findViewById(R.id.et_umur);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        et_jenis_kelamin = findViewById(R.id.et_jenis_kelamin);
        btnSave = findViewById(R.id.btn_save);

//        ArrayAdapter<String> adapterJenisKelamin = new ArrayAdapter<>(this, R.layout.item_list_register, JENIS_KELAMIN_LIST);
//        et_jenis_kelamin.setAdapter(adapterJenisKelamin);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    private void createUser(){
        PenggunaModels penggunaModels = new PenggunaModels(
                etNama.getText().toString(),
               etUmur.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                et_jenis_kelamin.getText().toString());
        String nama = etNama.getText().toString();
        String umur = etUmur.getText().toString();
        String email =  etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String jenisKelamin = et_jenis_kelamin.getText().toString();

        if (TextUtils.isEmpty(nama)) {
            etNama.setError("Name cannot be empty");
            etNama.requestFocus();
        }else if(TextUtils.isEmpty(umur)){
            etUmur.setError("Umur cannot be empty");
            etUmur.requestFocus();
        }else if(TextUtils.isEmpty(email)){
            etEmail.setError("Email cannot be empty");
            etEmail.requestFocus();
        }else if(TextUtils.isEmpty(jenisKelamin)){
            et_jenis_kelamin.setError("Jenis Kelamin cannot be empty");
            et_jenis_kelamin.requestFocus();
        }
        else if(TextUtils.isEmpty(password)) {
            etPassword.setError("Password can't be empty");
            etPassword.requestFocus();
        } else if(etUmur.getText().toString().isEmpty()) {
            etUmur.setError("Umur can't be empty");
            etUmur.requestFocus();
        }else{
            StringRequest stringRequest = new StringRequest(POST, RegisterUserApi.ADD_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    PenggunaResponse penggunaResponse = gson.fromJson(response, PenggunaResponse.class);
                    Toast.makeText(RegisterActivity.this, penggunaResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        JSONObject errors = new JSONObject(responseBody);
                        Toast.makeText(RegisterActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }){
                // Menambahkan header pada request
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Accept", "application/json");
                    return headers;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    Gson gson = new Gson();

                    String requestBody = gson.toJson(penggunaModels);
                    return requestBody.getBytes(StandardCharsets.UTF_8);
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            };
            // Menambahkan request ke request queue
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(stringRequest);
        }
    }

    @Override
    public String getNama() {
        return etNama.getText().toString();
    }

    @Override
    public void showNamaError(String message) {
        etNama.setError(message);
        etNama.requestFocus();
    }

    @Override
    public String getUmur() {
        return etUmur.getText().toString();
    }

    @Override
    public void showUmurError(String message) {
        etUmur.setError(message);
        etUmur.requestFocus();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public void showEmailError(String message) {
        etEmail.setError(message);
        etEmail.requestFocus();
    }

    @Override
    public String getJenisKelamin() {
        return et_jenis_kelamin.getText().toString();
    }

    @Override
    public void showJenisKelaminError(String message) {
        et_jenis_kelamin.setError(message);
        et_jenis_kelamin.requestFocus();

    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void showPasswordError(String message) {
        etPassword.setError(message);
        etPassword.requestFocus();
    }

    @Override
    public void startRegisterActivity() {
        new ActivityUtil(this).startRegisterActivity();
    }

    @Override
    public void showProfilError(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }

    @Override
    public void showErrorResponse(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }
}