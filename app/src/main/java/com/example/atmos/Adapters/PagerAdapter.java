package com.example.atmos.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {

    private Fragment fragment1, fragment2;

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity, Fragment fragment1, Fragment fragment2) {
        super(fragmentActivity);

        this.fragment1 = fragment1;
        this.fragment2 = fragment2;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return fragment1;
            case 1:
                return fragment2;
        }
        return fragment1;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
