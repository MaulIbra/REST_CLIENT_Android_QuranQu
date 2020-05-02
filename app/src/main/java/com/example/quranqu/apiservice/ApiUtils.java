package com.example.quranqu.apiservice;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public class ApiUtils {

    public static final String BASE_URL = "https://al-quran-8d642.firebaseio.com/";

    public static ApiServiceInterface getApiService(){
        return ApiClient.getClient(BASE_URL).create(ApiServiceInterface.class);
    }
}
