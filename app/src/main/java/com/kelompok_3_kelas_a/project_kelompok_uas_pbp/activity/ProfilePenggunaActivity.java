package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
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

import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.api.ApiInterface;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PenggunaModels;

import java.io.ByteArrayOutputStream;

public class ProfilePenggunaActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CAMERA = 100;
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_PICTURE = 1;
    private static final String[] JENIS_KELAMIN_LIST = new String[]{"Laki-laki", "Perempuan"};

    private EditText etNama, etUmur, etEmail;
    private ImageView ivGambar;
    private AutoCompleteTextView edJenisKelamin;
    private LinearLayout layoutLoading;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pengguna);
//        apiInterface = ApiClient.getClient().create(ApiInterface .class);
        ivGambar = findViewById(R.id.iv_gambar);
        etNama = findViewById(R.id.et_nama);
        etUmur = findViewById(R.id.et_umur);
        etEmail = findViewById(R.id.et_email);
        edJenisKelamin = findViewById(R.id.ed_jenis_kelamin);

        ArrayAdapter<String> adapterJenisKelamin = new ArrayAdapter<>(this, R.layout.item_list_pengguna, JENIS_KELAMIN_LIST);
        edJenisKelamin.setAdapter(adapterJenisKelamin);

        ivGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(ProfilePenggunaActivity.this);
                View selectMediaView = layoutInflater
                        .inflate(R.layout.layout_select_media_pengguna, null);

                final AlertDialog alertDialog = new AlertDialog
                        .Builder(selectMediaView.getContext()).create();

                Button btnKamera = selectMediaView.findViewById(R.id.btn_kamera);
                Button btnGaleri = selectMediaView.findViewById(R.id.btn_galeri);

                btnKamera.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) ==
                                PackageManager.PERMISSION_DENIED) {
                            String[] permission = {Manifest.permission.CAMERA};
                            requestPermissions(permission, PERMISSION_REQUEST_CAMERA);
                        } else {
                            // Membuka kamera
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_REQUEST);
                        }

                        alertDialog.dismiss();
                    }
                });

                btnGaleri.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Membuka galeri
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, GALLERY_PICTURE);

                        alertDialog.dismiss();
                    }
                });

                alertDialog.setView(selectMediaView);
                alertDialog.show();
            }
        });

        Button btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnSave = findViewById(R.id.btn_save);
        TextView tvTitle = findViewById(R.id.tv_title);
        long id = getIntent().getLongExtra("id", -1);

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
        tvTitle.setText("Edit Profile");
//            getPenggunaById(id);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile(id);
            }
        });
//        }
    }

    private String bitmapToBase64(Bitmap bitmap) {
        // TODO: Tambahkan fungsi untuk mengkonversi bitmap dengan output Base64 string hasil
        //  konversi. Gunakan fungsi ini saat menambah atau mengedit data produk.
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        return encoded;
    }

    private void updateProfile(long id){
        setLoading(true);

        PenggunaModels penggunaModels = new PenggunaModels(
                etNama.getText().toString(),
                Integer.parseInt(etUmur.getText().toString()),
                edJenisKelamin.getText().toString(),
                bitmapToBase64(((BitmapDrawable) ivGambar.getDrawable()).getBitmap()),
                etEmail.getText().toString()
        );
        //updateService

//        Call<PenggunaResponse> call = apiInterface.updateProduk(id, pengguna);
//
//        call.enqueue(new Callback<PenggunaResponse>() {
//            @Override
//            public void onResponse(Call<PenggunaResponse> call, Response<PenggunaResponse> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(ProfilPengguna.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    Intent returnIntent = new Intent();
//                    setResult(Activity.RESULT_OK, returnIntent);
//                    finish();
//                } else {
//                    try {
//                        JSONObject jsonObjectError = new JSONObject(response.errorBody().string());
//                        Toast.makeText(ProfilPengguna.this, jsonObjectError.getString("message"), Toast.LENGTH_SHORT).show();
//                    } catch (Exception e) {
//                        Toast.makeText(ProfilPengguna.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//                setLoading(false);
//            }
//
//            @Override
//            public void onFailure(Call<PenggunaResponse> call, Throwable t) {
//                Toast.makeText(ProfilPengguna.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                setLoading(false);
//            }
//        });
    }

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