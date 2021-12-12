package com.example.feedmewithfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SellerActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        rootNode = FirebaseDatabase.getInstance("https://feedme-55e8d-default-rtdb.firebaseio.com/");
        reference = rootNode.getReference("Sellers");
    }

    public void onConfirmSeller (View view){

        //push seller Item to database
        String eventName = ((TextView)findViewById(R.id.eventName)).getText().toString();
        String foodItem = ((TextView)findViewById(R.id.foodItem)).getText().toString();
        Double price = Double.parseDouble(((TextView)findViewById(R.id.sellerPrice)).getText().toString());
        String description = ((TextView)findViewById(R.id.description)).getText().toString();
        // For now assume mm/dd/yyyy format
        String startDate = ((TextView)findViewById(R.id.startDate)).getText().toString();
//        DatePicker startDate = ((DatePicker)findViewById(R.id.startDatePicker)).ge;
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        String strDate = dateFormat.format(date);
        String endDate = ((TextView)findViewById(R.id.endDate)).getText().toString();

        int seed = (int)(Math.random() * (999999999 - 100000000) + 100000000);
        String eventId = Integer.toString(seed); // TODO
//
        // Just make the sellerID same as username
        SharedPreferences pref = getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);
        String sellerId = pref.getString("username", "");
        String location = pref.getString("location",""); // this will be ""
        String lat = pref.getString("latitude", "");
        String lng = pref.getString("longitude", "");

        Log.d("test", eventName);
        Log.d("test", foodItem);
        Log.d("test", String.valueOf(price));
        Log.d("test", description);
        Log.d("test", startDate);
        Log.d("test", endDate);
        Log.d("test", sellerId);
        Log.d("test", location);

        SellerHelperClass helperClass = new SellerHelperClass(eventId, sellerId, eventName, foodItem, startDate, endDate, location, lat, lng, description, price);
        reference.child(eventId).setValue(helperClass);

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}