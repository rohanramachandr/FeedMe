package com.example.feedmewithfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyerFragment extends Fragment implements BuyerRecyclerAdapter.ItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private BuyerRecyclerAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<SellerHelperClass> list = new ArrayList<>();

    public BuyerFragment() {
        // Required empty public constructor
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Sellers");
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("test", "----------LOGGING onCreate----------");
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    SellerHelperClass s = ds.getValue(SellerHelperClass.class);
//                    list.add(s);
//                    Log.d("test", s.toString());
//                    Log.d("test", "lmao");
//                    Log.d("test", String.valueOf(list.size()));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuyerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuyerFragment newInstance(String param1, String param2) {
        BuyerFragment fragment = new BuyerFragment();
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
        return inflater.inflate(R.layout.fragment_buyer, container, false);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "clicked on " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        list.add(new BuyerRecyclerData("test title 1", 4.99, "1.001","1.001"));
//        list.add(new BuyerRecyclerData("test title 2", 1.49, "1.001","1.001"));
//        list.add(new BuyerRecyclerData("test title 3", 3.00, "1.001","1.001"));
        BuyerRecyclerAdapter.ItemClickListener listener = this;
        Context context = getContext();
        if(list.size() == 0) {
            list = new ArrayList<>();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Sellers");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Log.d("test", "----------LOGGING onViewCreated----------");
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        SellerHelperClass s = ds.getValue(SellerHelperClass.class);
                        list.add(s);
//                        Log.d("test1", s.latitude);
//                        Log.d("test1", s.longitude);
                    }
                    RecyclerView recyclerView = view.findViewById(R.id.buyerRecycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new BuyerRecyclerAdapter(context, getActivity(), list, recyclerView);
                    adapter.setClickListener(listener);
//        Log.d("test", adapter.getItem(1).toString());

                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

//        Log.d("test", "list size at this state: " + list.size());

    }
}