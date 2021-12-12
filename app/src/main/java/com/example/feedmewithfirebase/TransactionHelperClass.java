package com.example.feedmewithfirebase;

public class TransactionHelperClass {
    public TransactionHelperClass(){}
    String buyerId, sellerId, description, transactionId, time;
    boolean requestApproved;
    double price;

    public TransactionHelperClass(String buyerId, String sellerId, double price, String description, String transactionId, String time, boolean requestApproved) {
        this.buyerId= buyerId;
        this.sellerId = sellerId;
        this.price = price;
        this.description =  description;
        this.transactionId = transactionId;
        this.time = time;
        this.requestApproved = requestApproved;
    }
}
