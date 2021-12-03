package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.email;

import static com.android.volley.Request.Method.POST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
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
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.HalamanUtama;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.LoginApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public static final String SHARE_PREFS = "SharedPrefUser";
    public static final String SAVE_ID = "idUser";
    private Button btnRegister, btnLogin;
    private EditText etEmail, etPassword;
    private RequestQueue queue;
    private long id;
    private SharedPreferences sharedPreferences;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("SharedPrefUser", Context.MODE_PRIVATE);
        id = sharedPreferences.getLong("id", 0);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    public void loginUser(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        PenggunaModels penggunaModels = new PenggunaModels(email, password);
        if (TextUtils.isEmpty(email)){
            etEmail.setError("Email cannot be empty");
            etEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
        }else{
//            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//            Toast.makeText(LoginActivity.this, "Step 300", Toast.LENGTH_SHORT).show();
//            Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();
//            Toast.makeText(LoginActivity.this, password, Toast.LENGTH_SHORT).show();
//            StringRequest stringRequest = new StringRequest(POST, LoginApi.ADD_URL, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try {
//                        Toast.makeText(LoginActivity.this, "Step 0", Toast.LENGTH_SHORT).show();
//                        JSONObject jsonObject = new JSONObject(response);
//                        Toast.makeText(LoginActivity.this, "Step 1", Toast.LENGTH_SHORT).show();
//                        JSONObject userObject = jsonObject.getJSONObject("user"); /*kemungkinan error*/
//                        saveId(userObject.getLong("id"));
                        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(LoginActivity.this, "Step 300", Toast.LENGTH_SHORT).show();
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Step 200", Toast.LENGTH_SHORT).show();
                                    if(mAuth.getCurrentUser().isEmailVerified()){
                                        Toast.makeText(LoginActivity.this, password, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, HalamanUtama.class));
                                    }else{
                                        Toast.makeText(LoginActivity.this, "Please Verified Your Email Address", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(LoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    try {
//                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
//                        JSONObject errors = new JSONObject(responseBody);
//                        Toast.makeText(LoginActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
//                    } catch (Exception e) {
//                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }){
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    HashMap<String, String> headers = new HashMap<String, String>();
//                    headers.put("Accept", "application/json");
//                    return headers;
//                }
//
//
//
//                @Override
//                public byte[] getBody() throws AuthFailureError {
//                    Gson gson = new Gson();
//                    String requestBody = gson.toJson(penggunaModels);
//                    return requestBody.getBytes(StandardCharsets.UTF_8);
//                }
//
//                @Override
//                public String getBodyContentType() {
//                    return "application/json";
//                }
//            };
//            queue.add(stringRequest);
//            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    Toast.makeText(LoginActivity.this, "Step 300", Toast.LENGTH_SHORT).show();
//                    if (task.isSuccessful()){
//                        Toast.makeText(LoginActivity.this, "Step 200", Toast.LENGTH_SHORT).show();
//                        if(mAuth.getCurrentUser().isEmailVerified()){
//                            Toast.makeText(LoginActivity.this, password, Toast.LENGTH_SHORT).show();
//
//                        }else{
//                            Toast.makeText(LoginActivity.this, "Please Verified Your Email Address", Toast.LENGTH_SHORT).show();
//                        }
//                    }else{
//                        Toast.makeText(LoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }
    }

    public void saveId(long id){
        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(SHARE_PREFS, LoginActivity.this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SAVE_ID, id);
        editor.commit();
    }
}