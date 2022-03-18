package com.example.atmos.Adapters;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atmos.R;

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    public TextView date_tv, description_tv, max_tv, min_tv;
    public ImageView imageView;

    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);

        date_tv = itemView.findViewById(R.id.id_list_date);
        description_tv = itemView.findViewById(R.id.id_list_desc);
        max_tv = itemView.findViewById(R.id.id_list_maximum);
        min_tv = itemView.findViewById(R.id.id_list_minimum);
        imageView = itemView.findViewById(R.id.id_list_weather_icon);
    }
}
