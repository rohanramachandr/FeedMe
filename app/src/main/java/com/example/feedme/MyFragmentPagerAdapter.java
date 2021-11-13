package com.example.feedme;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.feedme.ProfileFragment;
import com.example.feedme.SettingsFragment;

public class MyFragmentPagerAdapter extends FragmentStateAdapter {
    public MyFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                return new ProfileFragment();
//            case 1:
//                return new SettingsFragment();
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return 2;
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ProfileFragment();
            case 1:
                return new SettingsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
