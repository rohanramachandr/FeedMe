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
//        Log.d("test", "Hit create");
          setContentView(R.layout.activity_seller);
//        Log.d("test", "load the xml");
//
//        rootNode = FirebaseDatabase.getInstance("https://feedme-55e8d-default-rtdb.firebaseio.com/");
//        reference = rootNode.getReference("Sellers");
//
//        Log.d("test","Got the ref");
//        Log.d("test", "GIVE IT A TRY");
        SellerHelperClass helperClass = new SellerHelperClass("13", "1", "Test Event", "Food", "12/11/2021", "12/11/2021", "", "Descrip", 40.0);
        reference.child("Sellers").setValue(helperClass);
//        Log.d("test", "PASSED");
    }

    public void onConfirmSeller (View view){

        Log.d("test","Hit onConfirmSeller!");

        //push seller Item to database

        //String eventName = ((TextView)findViewById(R.id.eventName)).getText().toString();
//        String foodItem = ((TextView)findViewById(R.id.foodItem)).getText().toString();
//        Double price = Double.parseDouble(((TextView)findViewById(R.id.sellerPrice)).getText().toString());
//        String description = ((TextView)findViewById(R.id.description)).getText().toString();
//        // For now assume mm/dd/yyyy format
//        String startDate = ((TextView)findViewById(R.id.startDate)).getText().toString();
//        DatePicker startDate = ((DatePicker)findViewById(R.id.startDatePicker)).ge;
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        String strDate = dateFormat.format(date);
//        String endDate = ((TextView)findViewById(R.id.endDate)).getText().toString();
//
//        String eventId = Integer.toString((int)Math.random()); // TODO
//
        // Just make the sellerID same as username
        SharedPreferences pref = getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);
        String sellerId = pref.getString("username", "");
//        String location = pref.getString("location","");

        Log.d("test","Got passed shared pref again!");
////
//       Log.d("test", eventName);
//        Log.d("test", foodItem);
//        Log.d("test", String.valueOf(price));
//        Log.d("test", description);
//        Log.d("test", startDate);
//        Log.d("test", endDate);
//        Log.d("test", sellerId);
//        Log.d("test", location);


        //SellerHelperClass helperClass = new SellerHelperClass(eventId, sellerId, eventName, foodItem, startDate, endDate, location, description, price);
        SellerHelperClass helperClass = new SellerHelperClass("0", "1", "Test Event", "Food", "12/11/2021", "12/11/2021", "", "Descrip", 40.0);
//        reference.getKey().setValue(1);
//        Log.d("test", "Write references vals:");
//        Log.d("test",reference.getKey());
//        Log.d("test",reference.child("Sellers").toString());
//        Log.d("test",reference.getParent().toString());
//        Log.d("test",reference.toString());

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}