package com.example.atmos.NetworkRequests;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;

import com.example.atmos.ModelClasses.LocationModel;
import com.example.atmos.ModelClasses.Weather;
import com.example.atmos.Response.CurrentWeather;
import com.example.atmos.Response.FutureWeather;
import com.example.atmos.Response.FutureWeather2;
import com.example.atmos.Utils.DateConversion;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiRequest {

    private MutableLiveData<CurrentWeather> currentWeather;
    private MutableLiveData<LocationModel> location;
    private MutableLiveData<List<FutureWeather2>> futureWeather;
    private LocationModel locationModel;
    private final String API_KEY = "fe08e3d621aef4ee2efb7fa8d0eeac5e";
    private FusedLocationProviderClient fusedLocationProviderClient;
    private String latitude = "";
    private String longitude = "";
    private final String units = "metric";
    private final String exclusions = "hourly,minutely,alerts,current";
    private static ApiRequest INSTANCE;

    private ApiClientMethods api = RetrofitClient.getRetrofitClient().create(ApiClientMethods.class);

    public ApiRequest() {
        currentWeather = new MutableLiveData<>();
        location = new MutableLiveData<>();
        futureWeather = new MutableLiveData<>();
    }

    public static ApiRequest getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiRequest();
        }
        return INSTANCE;
    }

    public MutableLiveData<CurrentWeather> getCurrentWeather() {
        return currentWeather;
    }

    public MutableLiveData<List<FutureWeather2>> getFutureWeather() {
        return futureWeather;
    }

    public MutableLiveData<LocationModel> getLocation(){
        return location;
    }

    //get user's current coordinates
    public void requestCurrentWeather(Context context) {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(@NonNull Location location) {
                    //Log.d(TAG, "onSuccess: " + String.valueOf(location.getLongitude()));
                    getLocation(location);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e.getMessage());
                }
            });
        }



    }

    public void getLocation(Location loc) {
        if (loc != null) {
            latitude = String.valueOf(loc.getLatitude());
            longitude = String.valueOf(loc.getLongitude());

            Log.d(TAG, "getLocation: " + latitude);

            LocationModel locationModel = new LocationModel(latitude, longitude);
            location.postValue(locationModel);

            getCurrentWeatherFromApi(latitude, longitude, API_KEY, units);
            getFutureWeatherFromApi(latitude, longitude, exclusions, API_KEY, units);

        }
    }

    public void getCurrentWeatherFromApi(String latitude, String longitude, String key, String units){
        Call<CurrentWeather> responseCall = api.getCurrentWeather(latitude,
                longitude,
                key,
                units);

        responseCall.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                currentWeather.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "onFailure: " + t.getMessage());
                currentWeather.postValue(null);
            }
        });
    }

    private void getFutureWeatherFromApi(String latitude, String longitude, String exclusions, String key, String units){

        Call<FutureWeather> responseCall = api.getFutureWeather(
                latitude,
                longitude,
                exclusions,
                key,
                units
        );

        responseCall.enqueue(new Callback<FutureWeather>() {
            @Override
            public void onResponse(Call<FutureWeather> call, Response<FutureWeather> response) {
                futureWeather.postValue(response.body().getDailyWeather());

            }

            @Override
            public void onFailure(Call<FutureWeather> call, Throwable t) {
                t.printStackTrace();
                futureWeather.postValue(null);
            }
        });
    }
}