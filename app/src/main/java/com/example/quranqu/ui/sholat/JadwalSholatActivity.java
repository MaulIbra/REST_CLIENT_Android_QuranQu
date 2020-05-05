package com.example.quranqu.ui.sholat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quranqu.R;
import com.example.quranqu.common.ConstantValue;
import com.example.quranqu.common.DateFormatHelper;
import com.example.quranqu.service.AlertReciever;

import java.util.Calendar;

/**
 * Created by Maulana Ibrahim on 05/May/2020
 * Email maulibrahim19@gmail.com
 */
public class JadwalSholatActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView btnBack;
    private TextView tvCityJadwal, tvDateJadwal, tvSubuh, tvDzuhur, tvAshr, tvMaghrib, tvIsya;
    private Switch swSubuh, swDzuhur, swAshr, swMaghrib, swIsya;
    private String subuh, dzuhur, ashr, maghrib, isya;
    DateFormatHelper dateFormatHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jadwal_activity);
        dateFormatHelper = new DateFormatHelper();
        onInitView();
        onInit();
    }

    private void onInit() {
        Intent i = getIntent();
        subuh = i.getStringExtra(ConstantValue.SUBUH);
        dzuhur = i.getStringExtra(ConstantValue.DZUHUR);
        ashr = i.getStringExtra(ConstantValue.ASHAR);
        maghrib = i.getStringExtra(ConstantValue.MAGHRIB);
        isya = i.getStringExtra(ConstantValue.ISYA);
        tvCityJadwal.setText(i.getStringExtra(ConstantValue.KOTA));
        tvDateJadwal.setText(i.getStringExtra(ConstantValue.TANGGAL));
        tvSubuh.setText(subuh);
        tvDzuhur.setText(dzuhur);
        tvAshr.setText(ashr);
        tvMaghrib.setText(maghrib);
        tvIsya.setText(isya);

    }

    private void onInitView() {
        btnBack = findViewById(R.id.backBtn);
        tvCityJadwal = findViewById(R.id.tvCityJadwal);
        tvDateJadwal = findViewById(R.id.tvDateJadwal);
        tvSubuh = findViewById(R.id.tvSubuh);
        tvDzuhur = findViewById(R.id.tvDzuhur);
        tvAshr = findViewById(R.id.tvAshr);
        tvMaghrib = findViewById(R.id.tvMaghrib);
        tvIsya = findViewById(R.id.tvIsya);
        swSubuh = findViewById(R.id.swSubuh);
        swDzuhur = findViewById(R.id.swDzuhur);
        swAshr = findViewById(R.id.swAshr);
        swMaghrib = findViewById(R.id.swMaghrib);
        swIsya = findViewById(R.id.swIsya);
        btnBack.setOnClickListener(this);
        swSubuh.setOnCheckedChangeListener(this);
        swDzuhur.setOnCheckedChangeListener(this);
        swAshr.setOnCheckedChangeListener(this);
        swMaghrib.setOnCheckedChangeListener(this);
        swIsya.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.swSubuh:
                alarmManager(b,subuh,1);
                break;
            case R.id.swDzuhur:
                alarmManager(b,dzuhur,2);
                break;
            case R.id.swAshr:
                alarmManager(b,ashr,3);
                break;
            case R.id.swMaghrib:
                alarmManager(b,maghrib,4);
                break;
            case R.id.swIsya:
                alarmManager(b,isya,5);
                break;
        }
    }

    private void alarmManager(Boolean x, String time, int reqCode) {
        if (x) {
            startAlarm(dateFormatHelper.convertToTimeCalendar(time), reqCode);
        } else {
            cancelAlarm(reqCode);
        }
    }

    private void startAlarm(Calendar c, int requestCode) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm(int requestCode) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

}
