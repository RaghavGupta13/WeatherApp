package com.example.atmos.WeatherViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.atmos.ModelClasses.LocationModel;
import com.example.atmos.Utils.LocationLiveData;

public class LocationViewModel extends ViewModel {

    private LocationLiveData locationLiveData;

    public LocationViewModel() {
        locationLiveData = LocationLiveData.getINSTANCE();
    }

    public MutableLiveData<LocationModel> getLocation(){
        return locationLiveData.getLocation();
    }

    public void getUpdatedLocation(Context context){
        locationLiveData.getUpdatedLocation(context);
    }

}
