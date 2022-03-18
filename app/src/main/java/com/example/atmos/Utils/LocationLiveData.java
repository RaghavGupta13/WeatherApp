package com.example.atmos.Utils;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.atmos.MainActivity;
import com.example.atmos.ModelClasses.LocationModel;
import com.example.atmos.NetworkRequests.ApiClientMethods;
import com.example.atmos.NetworkRequests.ApiRequest;
import com.example.atmos.NetworkRequests.RetrofitClient;
import com.example.atmos.Response.CurrentWeather;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationLiveData{

    private MutableLiveData<LocationModel> location;
    private static LocationLiveData INSTANCE;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private AppCompatActivity activity;
    private final int REQUEST_CODE = 101;
    private String latitude = "";
    private String longitude = "";
    private ApiRequest apiRequest;
    private final String API_KEY = "fe08e3d621aef4ee2efb7fa8d0eeac5e";
    private final String unit = "metric";
    private MutableLiveData<CurrentWeather> currentWeather;

    public LocationLiveData(){
        location = new MutableLiveData<>();
        currentWeather = new MutableLiveData<>();
    }

    public static LocationLiveData getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new LocationLiveData();
        }
        return INSTANCE;
    }

    public MutableLiveData<LocationModel> getLocation(){
        return location;
    }

    public void getUpdatedLocation(Context context){
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

    public void getLocation(Location loc){
        if(loc != null){
            latitude = String.valueOf(loc.getLatitude());
            longitude = String.valueOf(loc.getLongitude());

            Log.d(TAG, "getLocation: " + latitude);

            LocationModel locationModel = new LocationModel(latitude, longitude);
            location.postValue(locationModel);


//            ApiClientMethods api = RetrofitClient.getRetrofitClient().create(ApiClientMethods.class);
//
//            Call<CurrentWeather> responseCall = api.getCurrentWeather("37.774929",
//                    "-122.419418",
//                    API_KEY,
//                    unit);
//
//            responseCall.enqueue(new Callback<CurrentWeather>() {
//                @Override
//                public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
//                    Log.d(TAG, "onResponse: " + response.code());
//                    Log.d(TAG, "onResponse: " + response.body().getWeather().getWeather_status());
//                    currentWeather.postValue(response.body());
//                }
//
//                @Override
//                public void onFailure(Call<CurrentWeather> call, Throwable t) {
//
//                }
//            });

        }
    }

}
