package com.example.feedmewithfirebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SellerRecyclerAdapter extends RecyclerView.Adapter<SellerRecyclerAdapter.ViewHolder> {

    private List<SellerHelperClass> mData; // can replace this with a list of object
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Activity activity;
    private RecyclerView recyclerView;


    // data is passed into the constructor
    SellerRecyclerAdapter(Context context, Activity activity, List<SellerHelperClass> data, RecyclerView recyclerView) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.activity= activity;
        this.recyclerView = recyclerView;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.seller_recycler_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each
    // can populate this with more views
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SellerHelperClass item = mData.get(position);
        holder.foodName.setText(item.eventName);
        holder.foodPrice.setText(String.valueOf(item.price));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context contextViewHolder;


        TextView foodName;
        TextView foodPrice;

        ViewHolder(View itemView) {
            super(itemView);
            contextViewHolder = activity;
            foodName = itemView.findViewById(R.id.sellerFoodNameText);
            foodPrice = itemView.findViewById(R.id.sellerPriceText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
                // should open google maps activity here

                int position = recyclerView.getChildAdapterPosition(view);
                Intent intent = new Intent(contextViewHolder, verifyPurchase.class);

                intent.putExtra("eventId", mData.get(position).eventId);
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