package com.example.trivia.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trivia.R;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private List<CollectionItem> collectionItems;

    public CollectionAdapter(List<CollectionItem> collectionItems) {
        this.collectionItems = collectionItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_collection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CollectionItem item = collectionItems.get(position);
        holder.personName.setText(item.getPersonName());
        holder.amount.setText(String.format("LKR %,.2f", item.getAmount()));
        holder.status.setText(item.getStatus());
        // You would set the image here using Glide/Picasso
    }

    @Override
    public int getItemCount() {
        return collectionItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView personImage;
        TextView personName;
        TextView amount;
        TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personImage = itemView.findViewById(R.id.personImage);
            personName = itemView.findViewById(R.id.personName);
            amount = itemView.findViewById(R.id.amount);
            status = itemView.findViewById(R.id.status);
        }
    }

    public static class CollectionItem {
        private String personName;
        private double amount;
        private String status;
        private String imageUrl;

        public CollectionItem(String personName, double amount, String status, String imageUrl) {
            this.personName = personName;
            this.amount = amount;
            this.status = status;
            this.imageUrl = imageUrl;
        }

        // Getters
        public String getPersonName() { return personName; }
        public double getAmount() { return amount; }
        public String getStatus() { return status; }
        public String getImageUrl() { return imageUrl; }
    }
}