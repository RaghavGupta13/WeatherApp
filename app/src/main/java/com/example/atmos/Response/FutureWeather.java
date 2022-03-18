package com.example.atmos.Response;

import com.example.atmos.ModelClasses.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FutureWeather {

    @SerializedName("daily")
    private List<FutureWeather2> dailyWeather;

    public List<FutureWeather2> getDailyWeather() {
        return dailyWeather;
    }

    @Override
    public String toString() {
        return "FutureWeather{" +
                "dailyWeather=" + dailyWeather +
                '}';
    }
}
