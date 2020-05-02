package com.example.quranqu.ui.surah;

import com.example.quranqu.R;
import com.example.quranqu.apiservice.ApiServiceInterface;
import com.example.quranqu.apiservice.ApiUtils;
import com.example.quranqu.model.ResponseSurah;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public class SurahPresenter implements SurahContract.presenter {

    private SurahContract.view view;
    private List<ResponseSurah> surahs = new ArrayList<>();
    private ApiServiceInterface apiService = ApiUtils.getApiService();

    public SurahPresenter(SurahContract.view view) {
        this.view = view;
    }

    @Override
    public void getSurah() {
        apiService.requestSurah()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ResponseSurah>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ResponseSurah> responseSurahs) {
                        for (int i = 0; i < responseSurahs.size();i++){
                            ResponseSurah surah = new ResponseSurah();
                            surah.setNomor(responseSurahs.get(i).getNomor());
                            surah.setNama(responseSurahs.get(i).getNama());
                            surah.setAsma(responseSurahs.get(i).getAsma());
                            surah.setAyat(responseSurahs.get(i).getAyat());
                            surah.setArti(responseSurahs.get(i).getArti());
                            surah.setType(responseSurahs.get(i).getType());
                            surahs.add(surah);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFailure(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        view.onSuccessGetSurah(surahs);
                    }
                });
    }
}
