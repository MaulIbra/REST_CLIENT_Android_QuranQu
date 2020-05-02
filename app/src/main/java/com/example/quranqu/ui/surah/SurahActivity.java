package com.example.quranqu.ui.surah;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranqu.R;
import com.example.quranqu.model.ResponseSurah;
import com.example.quranqu.ui.surah.adapter.SurahAdapter;

import java.util.List;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public class SurahActivity extends AppCompatActivity implements SurahContract.view, View.OnClickListener {

    private SurahPresenter presenter;
    private ImageView backBtn;
    private EditText etSearch;
    private RecyclerView recyclerView;
    private SurahAdapter surahAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surah_activity);
        onInitView();
        onInit();
    }

    private void onInitView(){
        backBtn = findViewById(R.id.backBtn);
        etSearch = findViewById(R.id.etSerachSurah);
        recyclerView = findViewById(R.id.rvListSurah);
        presenter = new SurahPresenter(this);
        backBtn.setOnClickListener(this);
    }


    private void onInit(){
        presenter.getSurah();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onSuccessGetSurah(List<ResponseSurah> responseSurahs) {
        surahAdapter = new SurahAdapter(this,responseSurahs);
        recyclerView.setAdapter(surahAdapter);
        surahAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backBtn :
                onBackPressed();
                break;
        }
    }

    private void search(){
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
