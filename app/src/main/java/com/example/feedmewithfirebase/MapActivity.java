package com.example.feedmewithfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.feedme.ProfileActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     *
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
        SharedPreferences pref = getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);
        LatLng currLocation = new LatLng(Double.parseDouble(pref.getString("latitude", "")),
                Double.parseDouble(pref.getString("longitude", "")));
        mMap.addMarker(new MarkerOptions()
                .position(currLocation)
                .title("Your Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currLocation));
        LatLng targetLocation = new LatLng(Double.parseDouble(pref.getString("latitude", ""))-0.05,
                Double.parseDouble(pref.getString("longitude", ""))-0.05);
        mMap.addMarker(new MarkerOptions()
                .position(targetLocation)
                .title("TargetLocation"));

    }

    public void backButton(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openMapsButton(View v) {
//        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
//        String uri = "http://maps.google.com/maps?saddr=" + sourceLatitude + "," +
//                sourceLongitude + "&daddr=" + destinationLatitude + "," +
//                destinationLongitude  + " (" + "Where the party is at" + ")";
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//        intent.setPackage("com.google.android.apps.maps");
//        startActivity(intent);
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + "latitude" + "," + "longitude");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}



//public class MapActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapsFragment()).commit();
//
//    }
//}