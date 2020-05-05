package com.example.quranqu.service;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.os.ResultReceiver;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.quranqu.common.ConstantValue;
import com.example.quranqu.ui.home.HomeActivity;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import static com.example.quranqu.ui.home.HomeActivity.REQUEST_CODE_LOCATION_PERMISSION;

/**
 * Created by Maulana Ibrahim on 05/May/2020
 * Email maulibrahim19@gmail.com
 */
public class CurrentLocationService {

    private Context context;
    private ResultReceiver resultReceiver;
    private Double latitude, longitude;

    public CurrentLocationService(Context context, ResultReceiver resultReceiver) {
        this.context = context;
        this.resultReceiver = resultReceiver;
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(
                context.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION);
        } else {
            getCurrentLocation();
        }
    }

    public void getCurrentLocation() {
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.getFusedLocationProviderClient(context).requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                LocationServices.getFusedLocationProviderClient(context.getApplicationContext())
                        .removeLocationUpdates(this);
                if (locationResult != null && locationResult.getLocations().size() > 0) {
                    int latestLocationIndex = locationResult.getLocations().size() - 1;
                    latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                    longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                    Location location = new Location("providerNA");
                    location.setLatitude(latitude);
                    location.setLongitude(longitude);
                    fetchAddressFromLatitude(location);
                }
            }
        }, Looper.getMainLooper());
    }

    private void fetchAddressFromLatitude(Location location) {
        Intent intent = new Intent(context, FetchAddressIntentService.class);
        intent.putExtra(ConstantValue.RECEIVER, resultReceiver);
        intent.putExtra(ConstantValue.LOCATION_DATA_EXTRA, location);
        context.startService(intent);
    }
}
