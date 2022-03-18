package com.example.atmos.Response;

import com.example.atmos.ModelClasses.Weather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeather {

    @SerializedName("weather")
    @Expose()
    private List<Weather> weather;

    @SerializedName("main")
    @Expose()
    private Weather main_weather;

    @SerializedName("dt")
    @Expose()
    private long date;

    @SerializedName("sys")
    @Expose()
    private Weather geographical_info;

    @SerializedName("name")
    @Expose()
    private String city_name;

    public List<Weather> getWeather() {
        return weather;
    }

    public Weather getMain_weather() {
        return main_weather;
    }


    public long getDate() {
        return date;
    }

    public Weather getGeographical_info() {
        return geographical_info;
    }

    public String getCity_name() {
        return city_name;
    }
//
//    @Override
//    public String toString() {
//        return "CurrentWeather{" +
//                "weather=" + weather +
//                ", main_weather=" + main_weather +
//                ", date=" + date +
//                ", geographical_info=" + geographical_info +
//                ", city_name=" + city_name +
//                '}';
//    }
}
