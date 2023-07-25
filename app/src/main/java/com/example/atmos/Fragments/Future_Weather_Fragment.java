package com.example.atmos.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atmos.Adapters.CustomAdapter;
import com.example.atmos.R;
import com.example.atmos.Response.FutureWeather2;
import com.example.atmos.WeatherViewModels.WeatherViewModel;

import java.util.List;

public class Future_Weather_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private WeatherViewModel viewModel;

    public Future_Weather_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(getActivity()).get(WeatherViewModel.class);
        View v =  inflater.inflate(R.layout.fragment_future__weather_, container, false);
        recyclerView = v.findViewById(R.id.id_future_recycler_view);

        setupFutureWeather();

        return v;
    }

    private void setupFutureWeather(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getFutureWeather().observe(getViewLifecycleOwner(), new Observer<List<FutureWeather2>>() {
            @Override
            public void onChanged(List<FutureWeather2> futureWeather2s) {
                if(futureWeather2s != null){
                        adapter.setWeatherList(futureWeather2s);

                }
            }
        });
    }
}