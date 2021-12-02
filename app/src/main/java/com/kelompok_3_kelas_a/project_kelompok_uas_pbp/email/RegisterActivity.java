package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.email;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;

public class RegisterActivity extends AppCompatActivity {

    private static final String[] JENIS_KELAMIN_LIST = new String[]{"Laki-laki", "Perempuan"};

    private EditText etNama, etUmur, etEmail, etPassword;
    private AutoCompleteTextView edJenisKelamin;
    private Button btnSave;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        String nama = etNama.getText().toString();
        int umur = Integer.parseInt(etUmur.getText().toString());
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        edJenisKelamin.getText().toString();

        if(TextUtils.isEmpty(email)){
            etEmail.setError("Email cannot be empty");
            etEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            etPassword.setError("Password can't be empty");
            etPassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "User registered successfully, Please Check Your Email",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }
}