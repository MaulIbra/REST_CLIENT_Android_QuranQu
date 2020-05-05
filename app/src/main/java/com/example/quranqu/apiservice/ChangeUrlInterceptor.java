package com.example.quranqu.apiservice;

import android.util.Log;

import com.example.quranqu.common.ConstantValue;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Maulana Ibrahim on 04/May/2020
 * Email maulibrahim19@gmail.com
 */
public class ChangeUrlInterceptor implements Interceptor {

    private static final String TAG = "ChangeUrlInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl oldUrl = originalRequest.url();
        Request.Builder builder = originalRequest.newBuilder();

        List<String> headerValues = originalRequest.headers("url_name");
        if(null != headerValues && headerValues.size()>0){
            builder.removeHeader("url_name");
            String urlName = headerValues.get(0);
            HttpUrl newBaseUrl = null;
            if("quran".equals(urlName)){
                newBaseUrl = HttpUrl.parse(ConstantValue.BASE_URL_QURAN);
            }else if ("adzan".equals(urlName)){
                newBaseUrl = HttpUrl.parse(ConstantValue.BASE_URL_ADZAN);
            }

            Log.d(TAG, "newBaseUrl = "+newBaseUrl);

            HttpUrl newHttpUrl = oldUrl.newBuilder()
                    .scheme(newBaseUrl.scheme())
                    .host(newBaseUrl.host())
                    .port(newBaseUrl.port())
                    .build();

            Request newRequest = builder.url(newHttpUrl).build();
            return chain.proceed(newRequest);
        }else{
            return chain.proceed(originalRequest);
        }
    }

}
