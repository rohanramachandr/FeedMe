package com.example.feedmewithfirebase;

public class BuyerRecyclerData {
    private String foodTitle;
    private double price;
    private String distance;

    public BuyerRecyclerData(String foodTitle, double price, String distance) {
        this.foodTitle = foodTitle;
        this.price = price;
        this.distance = distance;
    }

    public BuyerRecyclerData(String foodTitle, double price) {
        this.foodTitle = foodTitle;
        this.price = price;
        this.distance = "Distance";
    }

    public String getTitle() {
        return foodTitle;
    }

    public double getPrice() {
        return price;
    }

    public String getDistance() {
        return distance;
    }

}
