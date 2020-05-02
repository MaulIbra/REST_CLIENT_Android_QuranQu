package com.example.quranqu.ui.surah;

import com.example.quranqu.model.ResponseSurah;

import java.util.List;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public interface SurahContract {

    interface view{
        void onSuccessGetSurah(List<ResponseSurah> responseSurahs);
        void onFailure(String message);
    }

    interface presenter{
        void getSurah();
    }

}
