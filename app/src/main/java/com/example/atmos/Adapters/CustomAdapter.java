package com.example.atmos.Adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.atmos.R;
import com.example.atmos.Response.FutureWeather;
import com.example.atmos.Response.FutureWeather2;
import com.example.atmos.Utils.DateConversion;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private List<FutureWeather2> weatherList;

    public CustomAdapter() {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item_layout, parent, false);
        return new WeatherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {

            FutureWeather2 current = weatherList.get(position);

            //date
            long unixTime = current.getDt();
            holder.date_tv.setText(DateConversion.convertToDate(unixTime, "dd MMMM, yyyy"));

            //description
            holder.description_tv.setText(current.getWeather().get(0).getWeather_status());

            //max & min temperatures
            holder.max_tv.setText(String.valueOf((int)current.getTemp().getTemp_max()));
            holder.min_tv.setText(String.valueOf((int)current.getTemp().getTemp_min()));

            //Weather icon
        Glide.with(holder.itemView.getContext())
                .load("https://openweathermap.org/img/wn/" + current.getWeather().get(0).getIcon() + "@2x.png")
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(weatherList != null){
            return weatherList.size();
        }else{
            return 0;
        }
    }

    public void setWeatherList(List<FutureWeather2> weatherList){
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }

}
