package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.AddEditTransaksiObatActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.ObatActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.activity.PendaftaranActivity;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.databinding.ItemObatTampilBinding;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.models.ObatModels;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.ViewHolder>
        implements Filterable {

    private List<ObatModels> obatModelsList, filteredObatList;
    private Context context;

    public ObatAdapter(List<ObatModels> obatModelsList, Context context) {
        this.obatModelsList = obatModelsList;
        filteredObatList = new ArrayList<>(obatModelsList);
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemObatTampilBinding binding;

        public ViewHolder(@NonNull ItemObatTampilBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ObatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemObatTampilBinding binding = DataBindingUtil.inflate(inflater,R.layout.item_obat_tampil, parent, false);

        return new ObatAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ObatAdapter.ViewHolder holder, int position) {
//        ObatModels obatModels = filteredObatList.get(position);
        ObatModels obatModels = obatModelsList.get(position);

//        holder.binding.tvNamaObat.setText(obatModels.getNamaObat());
//        holder.binding.tvStokObat.setText("Stok " + obatModels.getStokObat());
//
//        DecimalFormat rupiahFormat = (DecimalFormat) DecimalFormat
//                .getCurrencyInstance(new Locale("in", "ID"));
//        holder.binding.tvHargaObat.setText(rupiahFormat.format(obatModels.getHargaObat()));

        holder.binding.tvNamaObat.setText(obatModels.getNamaObat());
        holder.binding.tvStokObat.setText(String.valueOf(obatModels.getStokObat()));
        holder.binding.tvHargaObat.setText(String.valueOf(obatModels.getHargaObat()));

        Glide.with(holder.binding.getRoot())
                .load(obatModels.getGambarObat())
                .placeholder(R.drawable.no_image)
                .into(holder.binding.ivGambarObat);

        holder.binding.cvObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddEditTransaksiObatActivity.class);
                i.putExtra("id", obatModels.getIdObat());

                if (context instanceof ObatActivity)
                    ((ObatActivity) context).startActivityForResult(i,
                            ObatActivity.LAUNCH_ADD_ACTIVITY);
            }
        });
    }

    @Override
    public int getItemCount() {
        return obatModelsList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<ObatModels> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()) {
                    filtered.addAll(obatModelsList);
                } else {
                    for (ObatModels obatModels : obatModelsList) {
                        if (obatModels.getNamaObat().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(obatModels);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredObatList.clear();
                filteredObatList.addAll((List<ObatModels>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
}
