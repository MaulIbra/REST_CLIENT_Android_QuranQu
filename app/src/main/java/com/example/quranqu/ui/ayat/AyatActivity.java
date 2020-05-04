package com.example.quranqu.ui.ayat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranqu.R;
import com.example.quranqu.common.ConstantValue;
import com.example.quranqu.model.ResponseAyat;
import com.example.quranqu.service.MediaPlayers;
import com.example.quranqu.ui.ayat.adapter.AyatAdapter;

import java.util.List;

/**
 * Created by Maulana Ibrahim on 03/May/2020
 * Email maulibrahim19@gmail.com
 */
public class AyatActivity extends AppCompatActivity implements AyatContract.view, View.OnClickListener {

    private AyatContract.presenter presenter;
    private RecyclerView recyclerView;
    private ImageButton btnBack, btnPlay;
    private AyatAdapter ayatAdapter;
    private String nomorSurah, urlAudio, namaSurah;
    private MediaPlayers mediaPlayers;
    private MediaPlayer mediaPlayer;
    private TextView appbarName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayat_activity);
        Intent i = getIntent();
        nomorSurah = i.getStringExtra(ConstantValue.nomorSurat) + ".json";
        urlAudio = i.getStringExtra(ConstantValue.urlAudio);
        namaSurah = i.getStringExtra(ConstantValue.namaSurat);
        onInitView();
        onInit();
    }

    private void onInitView() {
        btnBack = findViewById(R.id.backBtn);
        btnPlay = findViewById(R.id.playAudio);
        appbarName = findViewById(R.id.appbarName);
        recyclerView = findViewById(R.id.rvListAyat);
        presenter = new AyatPresenter(this);
        mediaPlayer = new MediaPlayer();
        btnBack.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
    }

    private void onInit() {
        presenter.getAyat(nomorSurah);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        appbarName.setText(namaSurah);
        mediaPlayers = new MediaPlayers(this, mediaPlayer, btnPlay, urlAudio);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                onBackPressed();
                break;
            case R.id.playAudio:
                onAudioPlayer();
        }
    }

    @Override
    public void onSuccessGetAyat(List<ResponseAyat> ayats) {
        ayatAdapter = new AyatAdapter(ayats, this);
        recyclerView.setAdapter(ayatAdapter);
        ayatAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(AyatActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAudioPlayer() {
        if (!urlAudio.isEmpty() || urlAudio != null) {
            if (mediaPlayers.isPlay() != null){
                if(mediaPlayers.isPlay()){
                    mediaPlayers.pauseMedia();
                }else {
                    if(mediaPlayers.isStop()){
                        mediaPlayers.playMedia();
                    }else{
                        mediaPlayers.init();
                    }
                }
            }else{
                mediaPlayers.init();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayers!=null){
                mediaPlayers.pauseMedia();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mediaPlayers != null && mediaPlayers.isPlay() != null){
            if(!mediaPlayers.isPlay()){
                mediaPlayers.playMedia();
            }
        }
    }
}
