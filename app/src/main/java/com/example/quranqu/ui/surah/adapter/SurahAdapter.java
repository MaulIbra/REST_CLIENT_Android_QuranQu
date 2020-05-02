package com.example.quranqu.ui.surah.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranqu.R;
import com.example.quranqu.common.ConstantValue;
import com.example.quranqu.model.ResponseSurah;
import com.example.quranqu.ui.ayat.AyatActivity;

import java.util.List;

/**
 * Created by Maulana Ibrahim on 03/May/2020
 * Email maulibrahim19@gmail.com
 */
public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.ViewHolder> {

    public static final String TAG = "SurahAdapter";

    private Context context;
    private List<ResponseSurah> responseSurah;

    public SurahAdapter(Context context, List<ResponseSurah> responseSurah) {
        this.context = context;
        this.responseSurah = responseSurah;
    }

    @NonNull
    @Override
    public SurahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_surah, parent, false);
        return new SurahAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SurahAdapter.ViewHolder holder, int position) {
        final ResponseSurah surah = responseSurah.get(position);
        holder.tvNomorSurah.setText(surah.getNomor());
        holder.tvNamaSurah.setText(surah.getNama());
        holder.tvKotaAyat.setText(surah.getType() + " | " + surah.getAyat());
        holder.tvNamaSurahArab.setText(surah.getAsma());
        holder.tvSurahArti.setText(surah.getArti());
        holder.LlitemSurah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AyatActivity.class);
                intent.putExtra(ConstantValue.nomorSurat, surah.getNomor());
                intent.putExtra(ConstantValue.urlAudio, surah.getAudio());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return responseSurah.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNomorSurah, tvNamaSurah, tvKotaAyat, tvNamaSurahArab, tvSurahArti;
        private LinearLayout LlitemSurah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomorSurah = itemView.findViewById(R.id.tvNomorSurah);
            tvNamaSurah = itemView.findViewById(R.id.tvNamaSurah);
            tvKotaAyat = itemView.findViewById(R.id.tvKotaAyat);
            tvNamaSurahArab = itemView.findViewById(R.id.tvNamaSurahArab);
            tvSurahArti = itemView.findViewById(R.id.tvSurahArti);
            LlitemSurah = itemView.findViewById(R.id.LlitemSurah);
        }
    }
}
