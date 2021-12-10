package com.example.feedme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.feedmewithfirebase.HomeActivity;
import com.example.feedmewithfirebase.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private int userId = 0;

    MyFragmentPagerAdapter fpa;
    ViewPager2 viewPager;
    TabLayout tabLayout;

    TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d("test", "Activity started");
        viewPager = (ViewPager2) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

//        SharedPreferences pref = getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);
//
//        nameText = findViewById(R.id.mainName);
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
//        String username = pref.getString("username", "");
//        Query checkUser = reference.orderByChild("username").equalTo(username);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(snapshot.exists()){
//
//                    nameText.setError(null);
//                    nameText.setText(snapshot.child(username).child("firstName").getValue(String.class));
//
//                }
//                else {
//                    nameText.setError("This username does not exist");
//                    nameText.requestFocus();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        setPagerAdapter();
        setTabLayout();
    }

    private void setPagerAdapter() {
        fpa = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(fpa);
    }

    private void setTabLayout() {
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();
        tabLayout.getTabAt(0).setText("Profile");
        tabLayout.getTabAt(1).setText("Settings");
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
