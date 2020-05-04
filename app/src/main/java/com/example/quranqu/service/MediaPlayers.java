package com.example.quranqu.service;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.widget.ImageButton;

import com.example.quranqu.R;

/**
 * Created by Maulana Ibrahim on 03/May/2020
 * Email maulibrahim19@gmail.com
 */
public class MediaPlayers {

    private Context context;
    private MediaPlayer mediaPlayer;
    private ImageButton imageButton;
    private String urlAudio;
    private Boolean isPlay;
    private Boolean isStop;
    private Boolean isInit;

    public Boolean isInit() {
        return isInit;
    }

    public void setInit(Boolean init) {
        isInit = init;
    }

    public Boolean isPlay() {
        return isPlay;
    }

    public void setPlay(Boolean play) {
        isPlay = play;
    }

    public Boolean isStop() {
        return isStop;
    }

    public void setStop(Boolean stop) {
        isStop = stop;
    }

    public MediaPlayers(Context context, MediaPlayer mediaPlayer, ImageButton imageButton, String urlAudio) {
        this.context = context;
        this.mediaPlayer = mediaPlayer;
        this.imageButton = imageButton;
        this.urlAudio = urlAudio;
    }

    public void init(){
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(urlAudio);
            mediaPlayer.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if(!mediaPlayer.isPlaying()){
                        resetMediaPlayer();
                    }
                }
            });

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    setStop(true);
                    playMedia();
                }
            });
            mediaPlayer.prepareAsync();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void playMedia() {
        if(!mediaPlayer.isPlaying()){
            imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pause_circle_filled_black_24dp));
            mediaPlayer.start();
            setPlay(true);
        }
    }

    public void pauseMedia(){
        if (mediaPlayer.isPlaying()){
            imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_play_circle_filled_black_24dp));
            mediaPlayer.pause();
            setPlay(false);
        }
    }

    private void resetMediaPlayer(){
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.reset();
            setPlay(false);
            setStop(false);
            imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_play_circle_filled_black_24dp));
        }
    }

    public void clearMediaPlayer() {
        if (mediaPlayer != null){
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
            setPlay(false);
            setStop(false);
        }
    }


}
