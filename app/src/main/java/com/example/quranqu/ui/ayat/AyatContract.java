package com.example.quranqu.ui.ayat;

import com.example.quranqu.model.ResponseAyat;

import java.util.List;

/**
 * Created by Maulana Ibrahim on 03/May/2020
 * Email maulibrahim19@gmail.com
 */
public interface AyatContract {

    interface view{
        void onSuccessGetAyat(List<ResponseAyat> ayats);
        void onFailure(String message);
        void onAudioPlayer();
    }

    interface presenter{
        void getAyat(String nomorSurat);
    }

}
