package com.example.quranqu.ui.home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.quranqu.R;
import com.example.quranqu.common.ConstantValue;
import com.example.quranqu.common.DateFormatHelper;
import com.example.quranqu.model.Items;
import com.example.quranqu.service.CurrentLocationService;
import com.example.quranqu.ui.sholat.JadwalSholatActivity;
import com.example.quranqu.ui.surah.SurahActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener, HomeContract.view {

    private CardView cvMembacaQuran, cvWaktuSholat, cvKumpulanDoa;
    private TextView tvCity, tvDate, tvTime, tvKetSholat, tvLeftTime, tvMenuju;
    private ImageView ivCompas;
    private float currentDegree = 0f;
    private SensorManager mSensorManager;
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private ResultReceiver resultReceiver;
    private DateFormatHelper dateFormatHelper;
    List<Items> itemsList = new ArrayList<>();
    private static final String TAG = "HomeActivity";
    private HomePresenter presenter;
    private CurrentLocationService currentLocationService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        onInitView();
        onInit();
        cvMembacaQuran.setOnClickListener(this);
        cvWaktuSholat.setOnClickListener(this);
    }

    private void onInitView() {
        cvMembacaQuran = findViewById(R.id.cvMembacaQuran);
        cvWaktuSholat = findViewById(R.id.cvWaktuSholat);
        cvKumpulanDoa = findViewById(R.id.cvKumpulanDoa);
        ivCompas = findViewById(R.id.ivCompas);
        tvCity = findViewById(R.id.tvCity);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvKetSholat = findViewById(R.id.tvKetSholat);
        tvLeftTime = findViewById(R.id.tvLeftTime);
        tvMenuju = findViewById(R.id.tvMenuju);
    }

    private void onInit() {
        mSensorManager = (SensorManager) Objects.requireNonNull(this).getSystemService(Context.SENSOR_SERVICE);
        dateFormatHelper = new DateFormatHelper();
        resultReceiver = new AddressResultReciver(new Handler());
        currentLocationService = new CurrentLocationService(this, resultReceiver);
        presenter = new HomePresenter(this);
        currentLocationService.checkPermission();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvMembacaQuran:
                Intent i = new Intent(HomeActivity.this, SurahActivity.class);
                i.putExtra(ConstantValue.idActivity, ConstantValue.quran);
                startActivity(i);
                break;
            case R.id.cvWaktuSholat:
                if (itemsList != null) {
                    if (itemsList.size() > 0) {
                        Intent z = new Intent(HomeActivity.this, JadwalSholatActivity.class);
                        z.putExtra(ConstantValue.SUBUH, itemsList.get(0).getFajr());
                        z.putExtra(ConstantValue.DZUHUR, itemsList.get(0).getDhuhr());
                        z.putExtra(ConstantValue.ASHAR, itemsList.get(0).getAsr());
                        z.putExtra(ConstantValue.MAGHRIB, itemsList.get(0).getMaghrib());
                        z.putExtra(ConstantValue.ISYA, itemsList.get(0).getIsha());
                        z.putExtra(ConstantValue.KOTA, tvCity.getText().toString());
                        z.putExtra(ConstantValue.TANGGAL, tvDate.getText().toString());
                        startActivity(z);
                    }
                }
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float degree = Math.round(sensorEvent.values[0] - 270);
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        ra.setDuration(30);
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
        currentLocationService.checkPermission();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSuccessGetJadwalSholat(List<Items> items) {
        itemsList = items;
        presenter.getFindFirstJadwal(items);
    }

    @Override
    public void onSuccessGetFirstJadwal(String[] x) {
        tvTime.setText(x[1]);
        tvKetSholat.setText(x[0]);
        tvLeftTime.setText(x[2]);
        tvMenuju.setText("Menuju");
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                currentLocationService.getCurrentLocation();
            } else {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class AddressResultReciver extends ResultReceiver {

        public AddressResultReciver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == ConstantValue.SUCCESS_RESULT) {
                tvCity.setText(resultData.getString(ConstantValue.RESULT_DATA_KEY));
                tvDate.setText(dateFormatHelper.getCurrentDateString(ConstantValue.DATE_FORMAT));
                presenter.getJadwalSholat(resultData.getString(ConstantValue.RESULT_DATA_KEY));
            } else {
                Toast.makeText(HomeActivity.this, resultData.getString(ConstantValue.RESULT_DATA_KEY), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
