package com.example.feedmewithfirebase;

public class SellerHelperClass {
    String eventId, sellerId, eventName, foodItem, startTime, endTime, location, description;
    double price;


    public SellerHelperClass(){}

    public SellerHelperClass(String eventId, String sellerId, String eventName, String foodItem, String startTime, String endTime, String location, String description, double price) {
        this.eventId = eventId;
        this.sellerId = sellerId;
        this.eventName = eventName;
        this.foodItem = foodItem;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
        this.price = price;
    }


}
