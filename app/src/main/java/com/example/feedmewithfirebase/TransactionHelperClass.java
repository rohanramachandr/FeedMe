package com.example.feedmewithfirebase;

public class TransactionHelperClass {
    public TransactionHelperClass(){}
    public String transactionId, tokenId, eventId, buyerPhoneNumber;
    public boolean requestPending;

    public TransactionHelperClass(String transactionId, String tokenId, String eventId, String buyerPhoneNumber, boolean requestPending) {
        this.transactionId = transactionId;
        this.tokenId = tokenId;
        this.eventId = eventId;
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.requestPending = requestPending;
    }
}
