package com.example.quranqu.ui.ayat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranqu.R;
import com.example.quranqu.model.ResponseAyat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maulana Ibrahim on 03/May/2020
 * Email maulibrahim19@gmail.com
 */
public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.ViewHolder> {

    private List<ResponseAyat> responseAyats = new ArrayList<>();
    private Context context;

    public AyatAdapter(List<ResponseAyat> responseAyats, Context context) {
        this.responseAyats = responseAyats;
        this.context = context;
    }

    @NonNull
    @Override
    public AyatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ayat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AyatAdapter.ViewHolder holder, int position) {
        ResponseAyat ayat = responseAyats.get(position);
        holder.tvNomorAyat.setText(ayat.getNomor());
        holder.tvAyatArti.setText(ayat.getId());
        holder.tvAyatArab.setText(ayat.getAr());
    }

    @Override
    public int getItemCount() {
        return responseAyats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNomorAyat,tvAyatArab,tvAyatArti;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomorAyat = itemView.findViewById(R.id.tvNomorAyat);
            tvAyatArab = itemView.findViewById(R.id.tvAyatArab);
            tvAyatArti = itemView.findViewById(R.id.tvAyatArti);
        }
    }
}
