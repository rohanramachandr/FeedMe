package com.example.feedme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.example.feedmewithfirebase.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView mainName, nameText, emailText, phoneText, usernameText;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mainName = (TextView) view.findViewById(R.id.mainName);
        nameText = (TextView) view.findViewById(R.id.nameValue);
        phoneText = (TextView) view.findViewById(R.id.phoneValue);
        emailText = (TextView) view.findViewById(R.id.emailValue);
        usernameText = (TextView) view.findViewById(R.id.profileUsernameValue);

        SharedPreferences pref = this.getActivity().getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String username = pref.getString("username", "");
        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    nameText.setError(null);
                    nameText.setText(snapshot.child(username).child("firstName").getValue(String.class) +
                            " " + snapshot.child(username).child("lastName").getValue(String.class));
//                    phoneText.setText(snapshot.child(username).child("phone").getValue(String.class));
//                    emailText.setText(snapshot.child(username).child("email").getValue(String.class));
                    usernameText.setText(snapshot.child(username).child("username").getValue(String.class));
                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);

                }
                else {
                    nameText.setError("This username does not exist");
                    nameText.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}