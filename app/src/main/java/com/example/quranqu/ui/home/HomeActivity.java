package com.example.quranqu.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.quranqu.R;
import com.example.quranqu.common.ConstantValue;
import com.example.quranqu.ui.surah.SurahActivity;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cvMembacaQuran, cvWaktuSholat, cvKumpulanDoa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        onInitView();
        cvMembacaQuran.setOnClickListener(this);
    }

    private void onInitView() {
        cvMembacaQuran = findViewById(R.id.cvMembacaQuran);
        cvWaktuSholat = findViewById(R.id.cvWaktuSholat);
        cvKumpulanDoa = findViewById(R.id.cvKumpulanDoa);
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
}
