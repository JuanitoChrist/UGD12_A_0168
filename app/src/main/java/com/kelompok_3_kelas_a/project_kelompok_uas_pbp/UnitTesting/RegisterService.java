package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.UnitTesting;

import static com.android.volley.Request.Method.POST;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.RegisterUserApi;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.email.LoginActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.email.RegisterActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RegisterService {
    //    TODO 6: silahkan salin code ProfilService

    public Boolean getValid(final RegisterView view, PenggunaModels penggunaModels, RegisterCallback callback,RegisterActivity activity, FirebaseAuth mAuth, RequestQueue queue ) {
        final Boolean[] bool = new Boolean[1];
        register(view, penggunaModels, new RegisterCallback() {
            @Override
            public void onSuccess(boolean value, PenggunaModels penggunaModels) {
                bool[0] = true;
            }

            @Override
            public void onError() {
                bool[0] = false;
            }
        },activity, mAuth, queue);
        return bool[0];
    }

    public void register(final RegisterView view, PenggunaModels penggunaModels, final
    RegisterCallback callback, RegisterActivity activity, FirebaseAuth mAuth, RequestQueue queue) {
            StringRequest stringRequest = new StringRequest(POST, RegisterUserApi.ADD_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    PenggunaResponse penggunaResponse = gson.fromJson(response, PenggunaResponse.class);
                    Toast.makeText(activity, penggunaResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(RegisterActivity.this, "User registered successfully, Please Check Your Email",
//                            Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    mAuth.createUserWithEmailAndPassword(penggunaModels.getEmail(), penggunaModels.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(activity, "User registered successfully, Please Check Your Email",
                                                Toast.LENGTH_SHORT).show();
                                        activity.startActivity(new Intent(activity, LoginActivity.class));
                                        callback.onSuccess(true,
                                                penggunaModels);
                                    } else {
                                        Toast.makeText(activity, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        callback.onError();
                                        view.showProfilError(penggunaResponse.getMessage());
                                    }
                                }
                            });
                        }

                        ;
                    });
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        JSONObject errors = new JSONObject(responseBody);
                        Toast.makeText(activity, errors.getString("message"), Toast.LENGTH_SHORT).show();
                        callback.onError();
                    } catch (Exception e) {
                        Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
                        callback.onError();
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


