package com.example.atmos.Response;

import com.example.atmos.ModelClasses.Weather;

import java.util.List;

public class FutureWeather2 {

    private Weather temp;
    private List<Weather> weather;
    private long dt;

    public Weather getTemp() {
        return temp;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public long getDt() {
        return dt;
    }
}
