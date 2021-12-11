package com.example.feedmewithfirebase;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BuyerRecyclerAdapter extends RecyclerView.Adapter<BuyerRecyclerAdapter.ViewHolder> {

    private List<BuyerRecyclerData> mData; // can replace this with a list of object
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    BuyerRecyclerAdapter(Context context, List<BuyerRecyclerData> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
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
        String latitude =  pref.getString("latitude", "");
        String longitude =  pref.getString("longitude", "");
        String foodTextString = mData.get(position).getTitle();
        double priceString = mData.get(position).getPrice();
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

        ViewHolder(View itemView) {
            super(itemView);
            foodText = itemView.findViewById(R.id.foodText);
            priceText = itemView.findViewById(R.id.price);
            distanceText = itemView.findViewById(R.id.distance);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
                // should open google maps activity here
                Intent intent = new Intent(context, MapActivity.class);
//                intent.putExtra("lat", latitude);
//                intent.putExtra("long", longitude);
                context.startActivity(intent);
            }
        }
    }

    // convenience method for getting data at click position
    BuyerRecyclerData getItem(int id) {
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