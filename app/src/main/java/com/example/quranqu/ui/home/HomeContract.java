package com.example.quranqu.ui.home;

import com.example.quranqu.model.Items;
import com.example.quranqu.model.ResponseJadwalSholat;

import java.util.List;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public interface HomeContract {

    interface view{
        void onSuccessGetJadwalSholat(List<Items> items);
        void onFailure(String message);
        void onSuccessGetFirstJadwal(String[] x);
    }

    interface presenter{
        void getJadwalSholat(String kota);
        void getFindFirstJadwal(List<Items> items);
    }

}
