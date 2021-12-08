package com.example.feedmewithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.feedme.ProfileActivity;
import com.example.feedme.ProfileFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private NavigationBarView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavView = findViewById(R.id.bottomnav);
        bottomNavView.setOnItemSelectedListener(bottomNavFunction);

        // make buyer fragment default view upon login
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyerFragment()).commit();
        bottomNavView.setSelectedItemId(R.id.buy);
    }

    private NavigationBarView.OnItemSelectedListener bottomNavFunction = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment fragment = null;
            switch(item.getItemId()){
                case R.id.sell:
                    fragment = new SellerFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                    break;
                case R.id.buy:
//                    fragment = new MapsFragment();
                    fragment = new BuyerFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                    break;
                case R.id.settings: // PROFILE
                    //fragment = new ProfileFragment();
                    //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                    break;
            }
            return true;
        }
    };

    public void onClickProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onClickNotifications(View view) {
        Intent intent = new Intent(this, NotificationsActivity.class);
        startActivity(intent);
    }

}