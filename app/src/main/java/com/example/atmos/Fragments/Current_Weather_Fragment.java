package com.example.atmos.Fragments;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atmos.ModelClasses.LocationModel;
import com.example.atmos.R;
import com.example.atmos.Response.CurrentWeather;
import com.example.atmos.Utils.DateConversion;
import com.example.atmos.WeatherViewModels.WeatherViewModelSomething;


public class Current_Weather_Fragment extends Fragment {

    private FragmentActivity fragmentActivity;
    private final int REQUEST_CODE = 101;
    //private LocationViewModel locationViewModel;
    private WeatherViewModelSomething viewModel;

    //declaring layout views
    private TextView date_tv, temp_tv, feels_like_temp_tv, weather_status_tv, location_tv;
    private SwipeRefreshLayout swipeRefreshLayout;

    public Current_Weather_Fragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //locationViewModel = new ViewModelProvider(getActivity()).get(LocationViewModel.class);
        viewModel = new ViewModelProvider(getActivity()).get(WeatherViewModelSomething.class);
        View v = inflater.inflate(R.layout.fragment_current__weather_, container, false);
        date_tv = v.findViewById(R.id.id_date_tv);
        temp_tv = v.findViewById(R.id.id_current_temp);
        feels_like_temp_tv = v.findViewById(R.id.id_feels_like_temp);
        weather_status_tv = v.findViewById(R.id.id_weather_status);
        location_tv = v.findViewById(R.id.id_location);
        swipeRefreshLayout = v.findViewById(R.id.id_pull_to_refresh);

        checkLocationPermissions();
        pullToRefresh();
        return v;
    }

    private void checkLocationPermissions(){
        if(ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //get location
            getLocation();
            Log.d(TAG, "checkLocationPermissions: " + "permission is present");
        }else{
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE);
        }

    }



    private void getLocation(){
        viewModel.requestCurrentWeather(getActivity());
        observeLocationAndWeatherChange();

    }



    public void observeLocationAndWeatherChange(){
        viewModel.getLocation().observe(getViewLifecycleOwner(), new Observer<LocationModel>() {
            @Override
            public void onChanged(LocationModel locationModel) {
                new LocationModel(locationModel.getLatitude(), locationModel.getLongitude());
                Log.d(TAG, "onChanged: " + locationModel.getLatitude() + " " + locationModel.getLongitude());
            }
        });

        viewModel.getCurrentWeather().observe(getViewLifecycleOwner(), new Observer<CurrentWeather>() {
            @Override
            public void onChanged(CurrentWeather currentWeather) {

                //date
                long unixTime = currentWeather.getDate();
                date_tv.setText(String.valueOf(DateConversion.convertToDate(unixTime, "dd MMMM, yyyy")));

                //current temperature
                temp_tv.setText(String.valueOf((int)currentWeather.getMain_weather().getTemp()) + "°C");

                //feels like temperature
                feels_like_temp_tv.setText(String.valueOf((int)currentWeather.getMain_weather().getFeels_like()) + "°C");

                //weather status
                weather_status_tv.setText(String.valueOf(currentWeather.getWeather().get(0).getWeather_status()));

                //location
                location_tv.setText(String.valueOf(
                        currentWeather.getCity_name() + "," + currentWeather.getGeographical_info().getCountry()
                ));
            }
        });
    }

    private void pullToRefresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                observeLocationAndWeatherChange();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getActivity(), "Permission Granted", Toast.LENGTH_SHORT).show();
                getLocation();
            }

        }
        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }



}