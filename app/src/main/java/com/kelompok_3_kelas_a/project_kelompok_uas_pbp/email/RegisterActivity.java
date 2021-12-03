package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.email;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.RegisterUserApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private static final String[] JENIS_KELAMIN_LIST = new String[]{"Laki-laki", "Perempuan"};

    private EditText etNama, etUmur, etEmail, etPassword;
    private AutoCompleteTextView edJenisKelamin;
    private Button btnSave;
    private RequestQueue queue;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        queue = Volley.newRequestQueue(this);
        etNama = findViewById(R.id.et_nama);
        etUmur = findViewById(R.id.et_umur);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        edJenisKelamin = findViewById(R.id.ed_jenis_kelamin);
        btnSave = findViewById(R.id.btn_save);

        ArrayAdapter<String> adapterJenisKelamin = new ArrayAdapter<>(this, R.layout.item_list_register, JENIS_KELAMIN_LIST);
        edJenisKelamin.setAdapter(adapterJenisKelamin);

        mAuth = FirebaseAuth.getInstance();
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
                Integer.parseInt(etUmur.getText().toString()),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                edJenisKelamin.getText().toString());
        String email =  etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            etEmail.setError("Email cannot be empty");
            etEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            etPassword.setError("Password can't be empty");
            etPassword.requestFocus();
        }else{
            StringRequest stringRequest = new StringRequest(POST, RegisterUserApi.ADD_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    PenggunaResponse penggunaResponse = gson.fromJson(response, PenggunaResponse.class);
                    Toast.makeText(RegisterActivity.this, penggunaResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(RegisterActivity.this, "User registered successfully, Please Check Your Email",
//                            Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "User registered successfully, Please Check Your Email",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    };
                });
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
//            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()){
//                                StringRequest stringRequest = new StringRequest(POST, RegisterUserApi.ADD_URL, new Response.Listener<String>() {
//                                    @Override
//                                    public void onResponse(String response) {
//                                        Gson gson = new Gson();
//                                        PenggunaResponse mahasiswaResponse = gson.fromJson(response, PenggunaResponse.class);
////                                        Toast.makeText(RegisterActivity.this, mahasiswaResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                                        Toast.makeText(RegisterActivity.this, "User registered successfully, Please Check Your Email",
//                                                Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                                    }
//                                }, new Response.ErrorListener() {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        try {
//                                            String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
//                                            JSONObject errors = new JSONObject(responseBody);
//                                            Toast.makeText(RegisterActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
//                                        } catch (Exception e) {
//                                            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                }){
//                                    // Menambahkan header pada request
//                                    @Override
//                                    public Map<String, String> getHeaders() throws AuthFailureError {
//                                        HashMap<String, String> headers = new HashMap<String, String>();
//                                        headers.put("Accept", "application/json");
//                                        return headers;
//                                    }
//
//                                    @Override
//                                    public byte[] getBody() throws AuthFailureError {
//                                        Gson gson = new Gson();
//
//                                        String requestBody = gson.toJson(penggunaModels);
//                                        return requestBody.getBytes(StandardCharsets.UTF_8);
//                                    }
//
//                                    @Override
//                                    public String getBodyContentType() {
//                                        return "application/json";
//                                    }
//                                };
//                                // Menambahkan request ke request queue
//                                    queue.add(stringRequest);
//                            }else{
//                                Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        };
//                    });
//                }
//            });
        }
    }
}