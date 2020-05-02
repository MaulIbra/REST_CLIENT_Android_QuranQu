package com.example.quranqu.apiservice;

import com.example.quranqu.model.ResponseSurah;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public interface ApiServiceInterface {

    @GET("data.json")
    Observable<List<ResponseSurah>> requestSurah();

}
