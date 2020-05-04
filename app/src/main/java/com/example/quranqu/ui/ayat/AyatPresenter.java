package com.example.quranqu.ui.ayat;

import com.example.quranqu.apiservice.ApiServiceInterface;
import com.example.quranqu.apiservice.ApiUtils;
import com.example.quranqu.model.ResponseAyat;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Maulana Ibrahim on 03/May/2020
 * Email maulibrahim19@gmail.com
 */
public class AyatPresenter implements AyatContract.presenter {

    public static final String TAG = "AyatPresenter";
    private List<ResponseAyat> responseAyats = new ArrayList<>();
    private AyatContract.view view;
    private ApiServiceInterface apiService = ApiUtils.getApiService();

    public AyatPresenter(AyatContract.view view) {
        this.view = view;
    }

    @Override
    public void getAyat(String nomorSurat) {
        apiService.requestAyat(nomorSurat)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ResponseAyat>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ResponseAyat> ayats) {
                        for (int i = 0; i < ayats.size(); i++) {
                            responseAyats.add(new ResponseAyat(ayats.get(i).getTr(), ayats.get(i).getNomor(), ayats.get(i).getId(), ayats.get(i).getAr()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFailure(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        view.onSuccessGetAyat(responseAyats);
                    }
                });
    }
}
