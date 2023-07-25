package com.example.atmos.WeatherViewModels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.atmos.ModelClasses.LocationModel;
import com.example.atmos.Response.CurrentWeather;
import com.example.atmos.Response.FutureWeather2;
import com.example.atmos.WeatherRepository.WeatherRepository;

import java.util.List;

public class WeatherViewModel extends ViewModel {
    private WeatherRepository repositorySomething;

    public WeatherViewModel(){
        repositorySomething = WeatherRepository.getInstance();
    }

    public MutableLiveData<CurrentWeather> getCurrentWeather(){
        return repositorySomething.getCurrentWeather();
    }

    public MutableLiveData<LocationModel> getLocation(){
        return repositorySomething.getLocation();
    }

    public MutableLiveData<List<FutureWeather2>> getFutureWeather(){
        return repositorySomething.getFutureWeather();
    }

    public void requestCurrentWeather(Context context){
        repositorySomething.requestCurrentWeather(context);
    }
}
