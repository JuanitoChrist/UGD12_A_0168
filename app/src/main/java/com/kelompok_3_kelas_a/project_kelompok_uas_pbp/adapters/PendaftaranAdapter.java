package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.AddEditPendaftaranActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.PendaftaranActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.PendaftaranModels;

import java.util.ArrayList;
import java.util.List;

public class PendaftaranAdapter extends RecyclerView.Adapter<PendaftaranAdapter.ViewHolder>
        implements Filterable {

    private List<PendaftaranModels> pendaftaranModelsList, filteredPendaftaranList;
    private Context context;

    public PendaftaranAdapter(List<PendaftaranModels> pendaftaranModelsList, Context context) {
        this.pendaftaranModelsList = pendaftaranModelsList;
        filteredPendaftaranList = new ArrayList<>(pendaftaranModelsList);
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama_pendaftar, tv_tanggalLahir_pendaftar, tv_nomorHp_pendaftar,
                tv_tanggalPeriksa_pendaftar, tv_jenisKelamin_pendaftar, tv_keluhan_pendaftar, tv_dokter_pendaftar;
        ImageButton btn_deletePendaftaran;
        CardView cv_Pendaftaran;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nama_pendaftar = itemView.findViewById(R.id.tv_nama_pendaftar);
            tv_tanggalLahir_pendaftar = itemView.findViewById(R.id.tv_tanggalLahir_pendaftar);
            tv_nomorHp_pendaftar = itemView.findViewById(R.id.tv_nomorHp_pendaftar);
            tv_jenisKelamin_pendaftar = itemView.findViewById(R.id.tv_jenisKelamin_pendaftar);
            tv_keluhan_pendaftar = itemView.findViewById(R.id.tv_keluhan_pendaftar);

            btn_deletePendaftaran = itemView.findViewById(R.id.btn_deletePendaftaran);
            cv_Pendaftaran = itemView.findViewById(R.id.cv_Pendaftaran);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pendaftaran_tampil, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PendaftaranModels pendaftaranModels = filteredPendaftaranList.get(position);

        holder.tv_nama_pendaftar.setText(pendaftaranModels.getNamaPendaftar());
        holder.tv_tanggalLahir_pendaftar.setText(pendaftaranModels.getTanggalLahirPendaftar());
        holder.tv_nomorHp_pendaftar.setText(pendaftaranModels.getNomorHPPendaftar());
        holder.tv_jenisKelamin_pendaftar.setText(pendaftaranModels.getJenisKelaminPendaftar());
        holder.tv_keluhan_pendaftar.setText(pendaftaranModels.getKeluhanPendaftar());


        holder.btn_deletePendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder materialAlertDialogBuilder =
                        new MaterialAlertDialogBuilder(context);
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah anda yakin ingin menghapus pendaftaran ini?")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (context instanceof PendaftaranActivity)
                                    ((PendaftaranActivity) context).deletePendaftaran(pendaftaranModels.getId());
                            }
                        })
                        .show();
            }
        });

        holder.cv_Pendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddEditPendaftaranActivity.class);
                i.putExtra("id", pendaftaranModels.getId());

                if (context instanceof PendaftaranActivity)
                    ((PendaftaranActivity) context).startActivityForResult(i,
                            PendaftaranActivity.LAUNCH_ADD_ACTIVITY);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredPendaftaranList.size();
    }


    public void setPendaftaranModelsList(List<PendaftaranModels> pendaftaranModelsList) {
        this.pendaftaranModelsList = pendaftaranModelsList;
        filteredPendaftaranList = new ArrayList<>(pendaftaranModelsList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<PendaftaranModels> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()) {
                    filtered.addAll(pendaftaranModelsList);
                } else {
                    for (PendaftaranModels pendaftaranModels : pendaftaranModelsList) {
                        if (pendaftaranModels.getNamaPendaftar().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(pendaftaranModels);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredPendaftaranList.clear();
                filteredPendaftaranList.addAll((List<PendaftaranModels>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

}

