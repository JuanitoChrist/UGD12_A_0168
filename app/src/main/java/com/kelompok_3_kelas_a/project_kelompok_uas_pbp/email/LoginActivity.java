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
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.HalamanUtama;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.LoginApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaResponse;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.preferences.userPreferences;

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
    private userPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        userPreferences = new userPreferences(LoginActivity.this);
//        sharedPreferences = getSharedPreferences("SharedPrefUser", Context.MODE_PRIVATE);
//        id = sharedPreferences.getLong("id", 0);
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
//            startActivity(new Intent(LoginActivity.this, HalamanUtama.class));
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            StringRequest stringRequest = new StringRequest(POST, LoginApi.ADD_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject userObject = jsonObject.getJSONObject("user"); /*kemungkinan error*/
                        id = userObject.getLong("id");
                        userPreferences.setLogin(id);
                        startActivity(new Intent(LoginActivity.this, HalamanUtama.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null)
                    {
                        String jsonError = new String(networkResponse.data);
                        Toast.makeText(LoginActivity.this, jsonError, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "here", Toast.LENGTH_SHORT).show();
                    }
//
                }
            }){
//

                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();

                    params.put("email", email);
                    params.put("password", password);
//                    params.put("Authorization", "Bearer" + " " + TOKEN);
                    return params;
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
            queue.add(stringRequest);
//
        }
    }

    public void saveId(long id){
        userPreferences.setLogin(id);
    }
}