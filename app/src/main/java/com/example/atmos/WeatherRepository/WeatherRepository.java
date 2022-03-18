package com.example.atmos.WeatherRepository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.atmos.ModelClasses.LocationModel;
import com.example.atmos.ModelClasses.Weather;
import com.example.atmos.NetworkRequests.ApiRequest;
import com.example.atmos.Response.CurrentWeather;
import com.example.atmos.Response.FutureWeather;
import com.example.atmos.Response.FutureWeather2;

import java.util.List;

public class WeatherRepository {

    private static WeatherRepository INSTANCE;

    private ApiRequest apiRequest;

    public WeatherRepository(){
        apiRequest = ApiRequest.getInstance();
    }

    public static WeatherRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new WeatherRepository();
        }
        return INSTANCE;
    }

    public MutableLiveData<CurrentWeather> getCurrentWeather(){
        return apiRequest.getCurrentWeather();
    }

    public MutableLiveData<LocationModel> getLocation(){
        return apiRequest.getLocation();
    }

    public MutableLiveData<List<FutureWeather2>> getFutureWeather(){
        return apiRequest.getFutureWeather();
    }

    public void requestCurrentWeather(Context context){
        apiRequest.requestCurrentWeather(context);
    }
}
