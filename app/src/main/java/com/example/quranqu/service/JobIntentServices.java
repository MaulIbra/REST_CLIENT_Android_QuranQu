package com.example.quranqu.service;

import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

/**
 * Created by Maulana Ibrahim on 01/May/2020
 * Email maulibrahim19@gmail.com
 */
public class JobIntentServices extends JobIntentService {

    private static final String TAG ="QuranquJobIntentService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork");

        String jobId = intent.getStringExtra("jobId");

        for (int i = 0; i < 10; i++){
            Log.d(TAG, jobId + "-" + i);

            if(isStopped()) return;
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public boolean onStopCurrentWork() {
        return super.onStopCurrentWork();
    }
}
