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

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public static final String SHARE_PREFS = "SharedPrefUser";
    public static final String SAVE_ID = "idUser";
    public static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMTg5MDkwOTI4Y2I4NGQwNDE3YjVlMWQ4ZGQ0MDg2MzQ5MWQ1ZjgzNGNmMjY2ZWZhNmQ5N2NlMTc4MTFkZDAzZjAxNGY0NmMyYWU4Y2FmMTYiLCJpYXQiOjE2Mzg1OTExNDEuMzU0MTA4LCJuYmYiOjE2Mzg1OTExNDEuMzU0MTEyLCJleHAiOjE2NzAxMjcxNDEuMzQzMTc5LCJzdWIiOiI5Iiwic2NvcGVzIjpbXX0.uYBweEnfs8s26sQEEluVl9OB5oQcbwlkMTwbs3hPUmha_MiPqfTx4i5h7GcDvPH_9Ek-bg70cKNg8I3PsPZivU9htOKpjuYJ_lVY-aeAkEUrA4q_t7ToGwWPg-ah1yGBbJADgDwImr3nz6i-9zUPyvYQJ503moBk8tl9LdSZ2nWyWpzwjo1JDenZ9AC4mJlnrj-9bxlrNscApmQcC3j__r5NVp4bbLU0KFJXNyDqJpWbz0kHnM5Vr2S6LgPoRus8aus0id5Zu0FkwOhSJ8KEh1qxKmrAZfyfQPeUJz4hnDwOf3eT4rZS9fjBIHg57Snfp9o83MxlEblXHuk8yMGeH84AYuN44lUFmvS8lLB57tGcpvevAFPsE0_vdB30Y7R0E2jhDEKDKfPfTZmmJ0FwjNKyx2nn3ZnQn8NLfwBqBSJSsi7MujxrXTcl9dz6r1fT8fSYJkX5bta-Q6_mCI9vNGVVdmZBP2Cc5FQcXnhR_bOsmkmWACHtdU5ks9DLCFF6kqzrTTe2PtwMvZOaYcvl0SvKxcQ57TxNy2V40FwzYu0iGYYUMLmqR751FjYlQ-lovRWot5fy7J7jLc4iNMbH0wNIyime-_sDe_cuRV0BQeKStjk3nyIik1jMecGGm6_W2OhoPP8LBYkYDwQojI9kW5fcLhNdNcpGVqcZzc5Ylm8"
    ; private Button btnRegister, btnLogin;
    private EditText etEmail, etPassword;
    private RequestQueue queue;
    private long id;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
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
//            startActivity(new Intent(LoginActivity.this, HalamanUtama.class));
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            StringRequest stringRequest = new StringRequest(POST, LoginApi.ADD_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject userObject = jsonObject.getJSONObject("user"); /*kemungkinan error*/
                        startActivity(new Intent(LoginActivity.this, HalamanUtama.class));
                        saveId(userObject.getLong("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse networkResponse = error.networkResponse;
                    Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, password, Toast.LENGTH_SHORT).show();
                    if (networkResponse != null && networkResponse.data != null)
                    {
                        String jsonError = new String(networkResponse.data);
                        Toast.makeText(LoginActivity.this, jsonError, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "hai", Toast.LENGTH_SHORT).show();
                    }
//                    try {
//                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
//                        JSONObject errors = new JSONObject(responseBody);
//                        Toast.makeText(LoginActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
//                    } catch (Exception e) {
//                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
                }
            }){
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    HashMap<String, String> headers = new HashMap<String, String>();
//                    headers.put("Accept", "application/json");
//                    return headers;
//                }

                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();

                    params.put("email", email);
                    params.put("password", password);
                    params.put("Authorization", "Bearer" + " " + TOKEN);
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
        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(SHARE_PREFS, LoginActivity.this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SAVE_ID, id);
        editor.commit();
    }
}