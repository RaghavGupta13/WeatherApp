package com.example.atmos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.atmos.Adapters.PagerAdapter;
import com.example.atmos.Fragments.Current_Weather_Fragment;
import com.example.atmos.Fragments.Future_Weather_Fragment;
import com.example.atmos.ModelClasses.LocationModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 101;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private static MainActivity instance;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationModel locationModel;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.id_tab_layout);
        viewPager = findViewById(R.id.id_viewpager);


        setupViewPager();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void setupViewPager(){
        PagerAdapter adapter = new PagerAdapter(this,
                new Current_Weather_Fragment(),
                new Future_Weather_Fragment());

        viewPager.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position){
                    case 0:
                        tab.setText("Today");
                        break;
                    case 1:
                        tab.setText("8 Days");
                        break;
                    default:
                        tab.setText("Today");
                        break;
                }
            }
        });

        tabLayoutMediator.attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(tabLayout.getSelectedTabPosition() == 0){
            Current_Weather_Fragment currentWeatherFragment = new Current_Weather_Fragment();
            currentWeatherFragment.observeLocationAndWeatherChange();
        }
        return super.onOptionsItemSelected(item);
    }
}