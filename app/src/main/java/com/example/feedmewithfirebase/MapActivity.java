package com.example.feedmewithfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.feedme.ProfileActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
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

        SharedPreferences pref = getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);

        LatLng currLocation = new LatLng(Double.parseDouble(pref.getString("latitude", "")),
                Double.parseDouble(pref.getString("longitude", "")));
        Marker m1 = mMap.addMarker(new MarkerOptions()
                .position(currLocation)
                .title("Your Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currLocation));
        LatLng targetLocation = new LatLng(Double.parseDouble(getIntent().getStringExtra("lat")),
                Double.parseDouble(getIntent().getStringExtra("long")));
        Marker m2 = mMap.addMarker(new MarkerOptions()
                .position(targetLocation)
                .title("TargetLocation"));
        LatLng midPoint = getMidPoint(Double.parseDouble(pref.getString("latitude", "")),
                Double.parseDouble(pref.getString("longitude", "")),
                Double.parseDouble(getIntent().getStringExtra("lat")),
                Double.parseDouble(getIntent().getStringExtra("long")));

        // build a new camera to zoom properly
//        CameraPosition camera = new CameraPosition.Builder().target(midPoint).zoom(14).build();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        // add both markers locations to the builder
        builder.include(m1.getPosition());
        builder.include(m2.getPosition());
        LatLngBounds bounds = builder.build();

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 20);
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.animateCamera(cu);
            }
        });

    }

    private LatLng getMidPoint(double lat1, double long1, double lat2, double long2) {
        return new LatLng((lat1+lat2)/2, (long1+long2)/2);
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

    public void requestButton(View v) {
        // generate token
        String token = String.format("%05d", (int) (Math.random() * 99999));
        // populate the table with the new request
        // update token textview
        TextView tokenText = findViewById(R.id.tokenText);
        tokenText.setText(token);
        // set the constraintview to visible and hide the button
        ConstraintLayout layout = findViewById(R.id.tokenGroup);
        layout.setVisibility(View.VISIBLE);
        v.setVisibility(View.GONE);
    }

    public void cancelButton(View v) {
        ConstraintLayout layout = findViewById(R.id.tokenGroup);
        layout.setVisibility(View.GONE);
        Button requestButton = findViewById(R.id.requestButton);
        requestButton.setVisibility(View.VISIBLE);

        // cancel should do something
        // also save to persistent data? if theres already a query ongoing, then visible layouts should be different
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