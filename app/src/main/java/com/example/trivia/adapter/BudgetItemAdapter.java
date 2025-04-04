package com.example.trivia.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trivia.R;

import java.util.List;

public class BudgetItemAdapter extends RecyclerView.Adapter<BudgetItemAdapter.ViewHolder> {

    private List<BudgetItem> budgetItems;

    public BudgetItemAdapter(List<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_trip_budget, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BudgetItem item = budgetItems.get(position);
        holder.itemName.setText(item.getName());
        holder.itemAmount.setText(String.format("LKR %,.2f", item.getAmount()));
    }

    @Override
    public int getItemCount() {
        return budgetItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemAmount = itemView.findViewById(R.id.itemAmount);
        }
    }

    public static class BudgetItem {
        private String name;
        private double amount;

        public BudgetItem(String name, double amount) {
            this.name = name;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public double getAmount() {
            return amount;
        }
    }
}