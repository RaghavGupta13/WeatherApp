package com.example.atmos.NetworkRequests;

//api.openweathermap.org/data/2.5/weather?lat=42.036098&lon=-91.587219&appid=fe08e3d621aef4ee2efb7fa8d0eeac5e
//https://api.openweathermap.org/data/2.5/onecall?lat=33.44&lon=-94.04
// &exclude=hourly,minutely,alerts,current&appid=fe08e3d621aef4ee2efb7fa8d0eeac5e&units=metric

import com.example.atmos.ModelClasses.Weather;
import com.example.atmos.Response.CurrentWeather;
import com.example.atmos.Response.FutureWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClientMethods {

    @GET("weather")
    Call<CurrentWeather> getCurrentWeather(
            @Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("appid") String api_key,
            @Query("units") String unit);

    @GET("onecall")
    Call<FutureWeather> getFutureWeather(
            @Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("exclude") String exclusions,
            @Query("appid") String api_key,
            @Query("units") String units
    );
}
