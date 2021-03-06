package com.example.feedmewithfirebase;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import android.content.SharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellerFragment extends Fragment implements SellerRecyclerAdapter.ItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private SellerRecyclerAdapter adapter;
    ArrayList<SellerHelperClass> list;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String username;

    public SellerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellerFragment newInstance(String param1, String param2) {
        SellerFragment fragment = new SellerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences pref = getContext().getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);
        username = pref.getString("username", "");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SellerRecyclerAdapter.ItemClickListener listener = this;
        Context context = getContext();



        list = new ArrayList<>();
        if(list.size() == 0) {
            list = new ArrayList<>();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Sellers");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                    for (DataSnapshot ds : snapshot.getChildren()) {
                        SellerHelperClass s = ds.getValue(SellerHelperClass.class);
                        if (s.sellerId.equals(username))
                        list.add(s);

                    }
                    RecyclerView recyclerView = view.findViewById(R.id.sellerRecycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new SellerRecyclerAdapter(context, getActivity(), list, recyclerView);
                    adapter.setClickListener(listener);
//        Log.d("test", adapter.getItem(1).toString());

                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "clicked on " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seller, container, false);
    }


}