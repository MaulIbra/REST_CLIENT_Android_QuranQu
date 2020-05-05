package com.example.quranqu.apiservice;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public class ApiUtils {
    public static ApiServiceInterface getApiService(){
        return ApiClient.getClient().create(ApiServiceInterface.class);
    }
}
