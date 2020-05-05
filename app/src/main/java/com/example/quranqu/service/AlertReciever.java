package com.example.quranqu.service;

import android.app.NotificationChannel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import androidx.core.app.NotificationCompat;

import com.example.quranqu.R;

/**
 * Created by Maulana Ibrahim on 05/May/2020
 * Email maulibrahim19@gmail.com
 */
public class AlertReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1,nb.build());
        MediaPlayer mediaPlayer = MediaPlayer.create(context,R.raw.adzan);
        mediaPlayer.start();
    }
}
