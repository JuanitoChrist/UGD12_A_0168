package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.databinding.ActivityQrScannerMainBinding;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.databinding.ActivityQrscannerBinding;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.hardware.QRScannerActivity;

public class QR_Scanner_Main extends AppCompatActivity {

    private ActivityQrScannerMainBinding binding;
    private final ActivityResultLauncher<Intent> cameraResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                try {
                                    Intent intent = result.getData();
                                    String strQRRes =
                                            intent.getStringExtra("QR_RESULT");
                                    String[] res = strQRRes.split(";");
                                    binding.txtNama.setText(res[0]);

                                } catch (Exception e) {
                                    binding.txtNama.setText("");
                                    Toast.makeText(QR_Scanner_Main.this, "QR CODE TIDAK VALID!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrScannerMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnScan.setOnClickListener(v -> {
            cameraResult.launch(new Intent(this, QRScannerActivity.class));
        });
    }
}