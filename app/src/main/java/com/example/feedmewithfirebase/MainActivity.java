package com.example.feedmewithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permission = ActivityCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
        // if not ask for it
        if (permission == PackageManager.PERMISSION_DENIED){

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        }
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private Boolean validateUsername() {
        usernameEditText = findViewById(R.id.username);
        String username = usernameEditText.getText().toString();


        if (username.isEmpty()){
            usernameEditText.setError("Username cannot be empty");
            return false;
        }
        else if (!username.trim().equals(username)){
            usernameEditText.setError("White Spaces are not allowed");
            return false;
        }
        else {
            usernameEditText.setError(null);
            return true;
        }

    }

    private Boolean validatePassword() {
        passwordEditText = findViewById(R.id.password);
        String password = passwordEditText.getText().toString();

        if (password.isEmpty()){
            passwordEditText.setError("Username cannot be empty");
            return false;
        }
        else if (!password.trim().equals(password)){
            passwordEditText.setError("White Spaces are not allowed");
            return false;
        }
        else {
            passwordEditText.setError(null);
            return true;
        }
    }


    public void onClickHome(View view) {
        if (!validateUsername() || !validatePassword()) {
            return;

        }
        else {
            isUser();
        }



    }

    private void isUser() {
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        SharedPreferences pref = getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    usernameEditText.setError(null);
                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);

                    if (passwordFromDB.equals(password)){

                        passwordEditText.setError(null);
                        String firstNameFromDB = snapshot.child(username).child("firstName").getValue(String.class);
                        String lastNameFromDB = snapshot.child(username).child("lastName").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                        intent.putExtra("firstName", firstNameFromDB);
//                        intent.putExtra("lastName", lastNameFromDB);
//                        intent.putExtra("username", username);
//                        intent.putExtra("password", passwordFromDB);

                        pref.edit().putString("firstName", firstNameFromDB).apply();
                        pref.edit().putString("lastName", lastNameFromDB).apply();
                        pref.edit().putString("username", username).apply();
                        pref.edit().putString("password", passwordFromDB).apply();

                        startActivity(intent);

                    }
                    else {
                        passwordEditText.setError("This password Is Incorrect");
                        passwordEditText.requestFocus();
                    }

                }
                else {
                    usernameEditText.setError("This username does not exist");
                    usernameEditText.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}