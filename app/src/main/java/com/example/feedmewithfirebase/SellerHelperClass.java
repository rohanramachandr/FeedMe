package com.example.feedmewithfirebase;

import android.location.Location;
import android.util.Log;

public class SellerHelperClass {
    public String eventId, sellerId, eventName, foodItem, startTime, endTime, location, latitude, longitude, description;
    public double price;
    public String distance;

    public SellerHelperClass(){}

    public SellerHelperClass(String eventId, String sellerId, String eventName, String foodItem, String startTime, String endTime,
                             String location, String latitude, String longitude, String description, double price) {
        this.eventId = eventId;
        this.sellerId = sellerId;
        this.eventName = eventName;
        this.foodItem = foodItem;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.price = price;
    }

    public String getDistance(double currLatitude, double currLongitude) {
        float[] result = new float[1];
        Location.distanceBetween(currLatitude, currLongitude,
                Double.parseDouble(latitude), Double.parseDouble(longitude), result);
        double distance = result[0]*0.000621371192; // from meters to miles
//        Log.d("test2", String.valueOf(distance));
        if(distance < 0.1) {
            distance *= 5280;
            return (int)distance + " ft";
        } else {
            distance = Math.floor(distance * 10) / 10;
            return distance + " mi";
        }
    }


}
