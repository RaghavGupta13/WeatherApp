package com.example.atmos.ModelClasses;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("main")
    private String weather_status;

    private String icon;

    private String country;

    private float temp;
    private float feels_like;
    private float min;
    private float max;

    public float getTemp() {
        return temp;
    }

    public float getFeels_like() {
        return feels_like;
    }

    public float getTemp_min() {
        return min;
    }

    public float getTemp_max() {
        return max;
    }

    public String getIcon() {
        return icon;
    }


    public String getCountry() {
        return country;
    }

    public String getWeather_status() {
        return weather_status;
    }


}
