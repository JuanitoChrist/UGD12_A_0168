package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.databinding.TemplateFragmentBinding;

public class TemplateFragment extends Fragment {
    protected TemplateFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TemplateFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.chart.setProgressBar(binding.progressbar);
    }
    // jangan lupa set binding menjadi null apabila digunakan di fragment
    // agar tidak terjadi memory leak
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}