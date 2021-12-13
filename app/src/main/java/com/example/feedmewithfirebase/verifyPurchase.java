package com.example.feedmewithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class verifyPurchase extends AppCompatActivity {
    private String sellerId, eventId;
    ArrayList<SellerHelperClass> list;
    private Context context =this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_purchase);
        Intent intent = getIntent();


        eventId = intent.getStringExtra("eventId");
    }

    public void verifyPurchase(View v) {
        EditText phoneNumberEditText = findViewById(R.id.phoneNumber);
        EditText tokenEditText = findViewById(R.id.token);
        String phoneNumber = phoneNumberEditText.getText().toString();
        String token = tokenEditText.getText().toString();
        list = new ArrayList<>();
        if(list.size() == 0) {
            list = new ArrayList<>();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Transactions");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                    for (DataSnapshot ds : snapshot.getChildren()) {
                        TransactionHelperClass  t= ds.getValue(TransactionHelperClass.class);

                        if (t.eventId.equals(eventId) && t.requestPending){



                            if(t.tokenId.equals(token) && t.buyerPhoneNumber.equals(phoneNumber)) {
                                DatabaseReference updateData = FirebaseDatabase.getInstance()
                                        .getReference("Transactions")
                                        .child(t.transactionId);
                                updateData.child("requestPending").setValue(false);
                                Toast.makeText(verifyPurchase.this, "Your request has been approved", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(context, HomeActivity.class);


                                context.startActivity(intent);
                                return;

                            }

                        }


                    }

                    Toast.makeText(verifyPurchase.this, "There are currently no buyers with verified credentials interested in this item", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, HomeActivity.class);

                    context.startActivity(intent);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}