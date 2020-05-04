package com.example.quranqu.ui.home;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.quranqu.R;
import com.example.quranqu.common.ConstantValue;
import com.example.quranqu.ui.surah.SurahActivity;

import java.util.Objects;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    private CardView cvMembacaQuran, cvWaktuSholat, cvKumpulanDoa;
    private ImageView ivCompas;
    private float currentDegree = 0f;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        onInitView();
        onInit();
        cvMembacaQuran.setOnClickListener(this);
    }

    private void onInitView() {
        cvMembacaQuran = findViewById(R.id.cvMembacaQuran);
        cvWaktuSholat = findViewById(R.id.cvWaktuSholat);
        cvKumpulanDoa = findViewById(R.id.cvKumpulanDoa);
        ivCompas = findViewById(R.id.ivCompas);
    }

    private void onInit() {
        mSensorManager = (SensorManager) Objects.requireNonNull(this).getSystemService(Context.SENSOR_SERVICE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvMembacaQuran:
                Intent i = new Intent(HomeActivity.this, SurahActivity.class);
                i.putExtra(ConstantValue.idActivity, ConstantValue.quran);
                startActivity(i);
                break;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float degree = Math.round(sensorEvent.values[0]-270);
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        ra.setDuration(10);
        ra.setFillAfter(true);
        ivCompas.startAnimation(ra);
        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
