package com.example.feedmewithfirebase;

import android.location.Location;

public class BuyerRecyclerData {
    private String foodTitle;
    private double price;
    private String distance = "Distance";
    private String latitude = "0"; //location of seller
    private String longitude = "0";

    public BuyerRecyclerData(String foodTitle, double price, String latitude, String longitude) {
        this.foodTitle = foodTitle;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BuyerRecyclerData(String foodTitle, double price) {
        this.foodTitle = foodTitle;
        this.price = price;
    }

    public String getTitle() {
        return foodTitle;
    }

    public double getPrice() {
        return price;
    }

    /*
     pass in location of buyer, and returns distance to seller
     */
    public String getDistance(double targetLatitude, double targetLongitude) {
        float[] result = new float[1];
        Location.distanceBetween(targetLatitude, targetLongitude,
                Double.parseDouble(latitude), Double.parseDouble(longitude), result);
        return String.valueOf(result[0]);

    }

}
