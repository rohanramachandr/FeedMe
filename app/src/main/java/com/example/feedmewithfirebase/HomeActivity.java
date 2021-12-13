package com.example.feedmewithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.feedme.ProfileActivity;
import com.example.feedme.ProfileFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private NavigationBarView bottomNavView;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 12;
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
//        username = intent.getStringExtra("username");
        SharedPreferences pref = getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);
        username = pref.getString("username", "");

        bottomNavView = findViewById(R.id.bottomnav);
        bottomNavView.setOnItemSelectedListener(bottomNavFunction);

        // make buyer fragment default view upon login
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyerFragment()).commit();
        bottomNavView.setSelectedItemId(R.id.buy);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        updateLocation();
    }

    private NavigationBarView.OnItemSelectedListener bottomNavFunction = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
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

    public void updateLocation() {
        SharedPreferences pref = getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);

        int permission = ActivityCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
        // if not ask for it
        if (permission == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        } else {
            mFusedLocationProviderClient.getLastLocation()
                    .addOnCompleteListener(this, task -> {
                        Location mLastKnownLocation = task.getResult();
                        String longitude;
                        String latitude;
                        if (task.isSuccessful() && mLastKnownLocation != null) {

                            longitude = String.valueOf(mLastKnownLocation.getLongitude());
                            latitude = String.valueOf(mLastKnownLocation.getLatitude());
                            pref.edit().putString("longitude", longitude).apply();
                            pref.edit().putString("latitude", latitude).apply();
                            Log.i("longitude", longitude);
                            Log.i("latitude", latitude);

                            DatabaseReference updateData = FirebaseDatabase.getInstance()
                                    .getReference("Users")
                                    .child(username);

                            updateData.child("latitude").setValue(latitude);
                            updateData.child("longitude").setValue(longitude);
                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mFusedLocationProviderClient.getLastLocation()
                            .addOnCompleteListener(this, task -> {
                                Location mLastKnownLocation = task.getResult();

                                SharedPreferences pref = getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);
                                String longitude;
                                String latitude;
                                longitude = String.valueOf(mLastKnownLocation.getLongitude());
                                latitude = String.valueOf(mLastKnownLocation.getLatitude());
                                pref.edit().putString("longitude", longitude).apply();
                                pref.edit().putString("latitude", latitude).apply();
                            });
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void onAddItemToSell(View view) {
        Intent intent = new Intent(this, SellerActivity.class);
        startActivity(intent);
    }

}