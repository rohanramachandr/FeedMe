package com.example.feedmewithfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText regFirstName, regLastName, regUsername, regPassword, regConfirmPassword, regPhoneNumber;



    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rootNode = FirebaseDatabase.getInstance("https://feedme-55e8d-default-rtdb.firebaseio.com/");
        reference = rootNode.getReference("Users");
    }

    private boolean checkIfValid(EditText reg, String field){
        if (field.isEmpty()){
            reg.setError("Username cannot be empty");
            return false;
        }
        else if (!field.trim().equals(field)){
            reg.setError("White Spaces are not allowed");
            return false;
        }
        else {
            return true;
        }
    }




    public void createAccount(View view) {

        regFirstName = findViewById(R.id.firstName);
        regLastName = findViewById(R.id.lastName);
        regUsername = findViewById(R.id.username);
        regPassword = findViewById(R.id.password);
        regConfirmPassword = findViewById(R.id.password);
        regPhoneNumber = findViewById(R.id.phoneNumber);

        String firstName = regFirstName.getText().toString();
        String lastName = regLastName.getText().toString();
        String username = regUsername.getText().toString();
        String phoneNumber = regPhoneNumber.getText().toString();
        
        String password = regPassword.getText().toString();
        String confirmPassword = regConfirmPassword.getText().toString();

        //Put phone number to shared preferences
        SharedPreferences pref = getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);
        pref.edit().putString("phoneNumber", phoneNumber).apply();

        UserHelperClass helperClass = new UserHelperClass(firstName, lastName, phoneNumber, username, password, "", "");
        reference.child(username).setValue(helperClass);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
}