package com.example.quranqu.ui.home;

import com.example.quranqu.apiservice.ApiServiceInterface;
import com.example.quranqu.apiservice.ApiUtils;
import com.example.quranqu.common.ConstantValue;
import com.example.quranqu.common.DateFormatHelper;
import com.example.quranqu.model.Items;
import com.example.quranqu.model.ResponseJadwalSholat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public class HomePresenter implements HomeContract.presenter {

    ApiServiceInterface apiInterface = ApiUtils.getApiService();
    List<Items> itemsList = new ArrayList<>();
    DateFormatHelper dateFormatHelper = new DateFormatHelper();
    HomeContract.view view;

    public HomePresenter(HomeContract.view view) {
        this.view = view;
    }

    @Override
    public void getJadwalSholat(String kota) {
        apiInterface.requestJadwalSholat(kota)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseJadwalSholat>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseJadwalSholat responseJadwalSholat) {

                        for (int i = 0; i < responseJadwalSholat.getItems().size(); i++) {
                            Items items = new Items();
                            items.setShurooq(dateFormatHelper.convertIndTime(responseJadwalSholat.getItems().get(i).getShurooq()));
                            items.setFajr(dateFormatHelper.convertIndTime(responseJadwalSholat.getItems().get(i).getFajr()));
                            items.setDhuhr(dateFormatHelper.convertIndTime(responseJadwalSholat.getItems().get(i).getDhuhr()));
                            items.setAsr(dateFormatHelper.convertIndTime(responseJadwalSholat.getItems().get(i).getAsr()));
                            items.setMaghrib(dateFormatHelper.convertIndTime(responseJadwalSholat.getItems().get(i).getMaghrib()));
                            items.setIsha(dateFormatHelper.convertIndTime(responseJadwalSholat.getItems().get(i).getIsha()));
                            itemsList.add(items);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFailure(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        view.onSuccessGetJadwalSholat(itemsList);
                    }
                });
    }

    @Override
    public void getFindFirstJadwal(List<Items> items) {
        String currentTime = dateFormatHelper.getCurrentDateString("H:mm:ss");
        List<String> item = new ArrayList<>();
        String[] hasil = new String[3];
        String[] sholat = {"Shubuh","Dzuhur","Ashr","Maghrib","Isya"};
        for (int i =0; i<items.size();i++){
            item.add(items.get(i).getFajr());
            item.add(items.get(i).getDhuhr());
            item.add(items.get(i).getAsr());
            item.add(items.get(i).getMaghrib());
            item.add(items.get(i).getIsha());
        }

        for (int i = 0; i< item.size();i++){
            if (dateFormatHelper.compareTwoTime(currentTime,item.get(i))){
                Calendar x = Calendar.getInstance();
                x = dateFormatHelper.getDiffTime(dateFormatHelper.convertToTimeCalendar(currentTime),dateFormatHelper.convertToTimeCalendar(item.get(i)));
                hasil[0] = sholat[i];
                hasil[1] = item.get(i);
                hasil[2] = x.get(Calendar.HOUR_OF_DAY)+" Jam "+x.get(Calendar.MINUTE)+" Menit";
                view.onSuccessGetFirstJadwal(hasil);
                break;
            }
        }
    }
}
