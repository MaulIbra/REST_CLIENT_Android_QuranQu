package com.example.quranqu.service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;

import androidx.annotation.Nullable;

import com.example.quranqu.common.ConstantValue;

import java.util.List;
import java.util.Locale;

/**
 * Created by Maulana Ibrahim on 04/May/2020
 * Email maulibrahim19@gmail.com
 */
public class FetchAddressIntentService extends IntentService {

    private ResultReceiver resultReceiver;

    public FetchAddressIntentService() {
        super("FetchAddressIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String errorMessage = "";
            resultReceiver = intent.getParcelableExtra(ConstantValue.RECEIVER);
            Location location = intent.getParcelableExtra(ConstantValue.LOCATION_DATA_EXTRA);
            if (location == null) {
                return;
            }
            Geocoder geocoder = new Geocoder(this, new Locale("id", "ID"));
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (Exception e) {
                errorMessage = e.toString();
            }
            if (addresses == null || addresses.isEmpty()) {
                deliverResultToReciver(ConstantValue.FAILURE_RESULT, errorMessage);
            } else {
                Address address = addresses.get(0);
                String[] aSplit = address.getAddressLine(0).split(",");
                deliverResultToReciver(ConstantValue.SUCCESS_RESULT,aSplit[aSplit.length-3].trim()) ;
            }
        }
    }

    private void deliverResultToReciver(int resultCode, String addressMessage) {
        Bundle bundle = new Bundle();
        bundle.putString(ConstantValue.RESULT_DATA_KEY, addressMessage);
        resultReceiver.send(resultCode, bundle);
    }
}
