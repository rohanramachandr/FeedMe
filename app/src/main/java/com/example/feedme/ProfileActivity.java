package com.example.feedme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.feedmewithfirebase.HomeActivity;
import com.example.feedmewithfirebase.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ProfileActivity extends AppCompatActivity {

    private int userId = 0;

    MyFragmentPagerAdapter fpa;
    ViewPager2 viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d("test", "Activity started");
        viewPager = (ViewPager2) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        setPagerAdapter();
        setTabLayout();
    }

    private void setPagerAdapter() {
        fpa = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(fpa);
    }

    private void setTabLayout() {
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();
        tabLayout.getTabAt(0).setText("Profile");
        tabLayout.getTabAt(1).setText("Settings");
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
