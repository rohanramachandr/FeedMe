package com.example.feedmewithfirebase;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BuyerRecyclerAdapter extends RecyclerView.Adapter<BuyerRecyclerAdapter.ViewHolder> {

    private List<SellerHelperClass> mData; // can replace this with a list of object
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private Activity activity;
    private RecyclerView v;

    // data is passed into the constructor
    BuyerRecyclerAdapter(Context context, Activity activity, List<SellerHelperClass> data, RecyclerView v) {
        this.context = context;
        this.activity = activity;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.v = v;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.buyer_recycler_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    // can populate this with more views
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SharedPreferences pref = context.getSharedPreferences("com.example.feedme", Context.MODE_PRIVATE);
        String latitude =  pref.getString("latitude", "0");
        String longitude =  pref.getString("longitude", "0");
        String foodTextString = mData.get(position).eventName;
        double priceString = mData.get(position).price;
        String distanceString = mData.get(position).getDistance(Double.valueOf(latitude), Double.valueOf(longitude));
        holder.foodText.setText(foodTextString);
        holder.priceText.setText(String.valueOf(priceString));
        holder.distanceText.setText(distanceString);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView foodText;
        TextView priceText;
        TextView distanceText;

        private final Context contextViewHolder;

        ViewHolder(View itemView) {
            super(itemView);
            contextViewHolder = activity;
            foodText = itemView.findViewById(R.id.foodText);
            priceText = itemView.findViewById(R.id.price);
            distanceText = itemView.findViewById(R.id.distance);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
//                mClickListener.onItemClick(view, getAdapterPosition());
                // should open google maps activity here

                int position = v.getChildAdapterPosition(view);
                Intent intent = new Intent(contextViewHolder, MapActivity.class);
                intent.putExtra("lat", mData.get(position).latitude);
                intent.putExtra("long", mData.get(position).longitude);
                contextViewHolder.startActivity(intent);

            }
        }
    }

    // convenience method for getting data at click position
    SellerHelperClass getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}