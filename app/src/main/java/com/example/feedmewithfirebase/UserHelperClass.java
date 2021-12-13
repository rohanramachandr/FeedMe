package com.example.feedmewithfirebase;

public class UserHelperClass {
    public String firstName, lastName, username, phoneNumber, password, latitude, longitude;

    public UserHelperClass(){}

    public UserHelperClass(String firstName, String lastName, String phoneNumber, String username,  String password, String latitude, String longitude) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
