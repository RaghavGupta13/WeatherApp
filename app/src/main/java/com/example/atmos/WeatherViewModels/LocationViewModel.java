package com.example.atmos.WeatherViewModels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.atmos.ModelClasses.LocationModel;
import com.example.atmos.Utils.LocationLiveData;

public class LocationViewModel extends ViewModel {

    private LocationLiveData locationLiveDataSomething;

    public LocationViewModel() {
        locationLiveDataSomething = LocationLiveData.getINSTANCE();
    }

    public MutableLiveData<LocationModel> getLocation(){
        return locationLiveDataSomething.getLocation();
    }

    public void getUpdatedLocation(Context context){
        locationLiveDataSomething.getUpdatedLocation(context);
    }

}
