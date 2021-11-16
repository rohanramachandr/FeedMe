package com.example.feedmewithfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapsFragment()).commit();

    }
}