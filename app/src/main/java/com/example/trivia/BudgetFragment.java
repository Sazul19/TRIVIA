package com.example.trivia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trivia.adapter.BudgetItemAdapter;
import com.example.trivia.adapter.CollectionAdapter;

import java.util.ArrayList;
import java.util.List;

public class BudgetFragment extends Fragment {

    private RecyclerView budgetItemsRecyclerView;
    private RecyclerView collectionRecyclerView;
    private BudgetItemAdapter budgetItemAdapter;
    private CollectionAdapter collectionAdapter;

    public BudgetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_budget, container, false);

        // Initialize RecyclerViews
        budgetItemsRecyclerView = view.findViewById(R.id.budgetItemsRecyclerView);
        collectionRecyclerView = view.findViewById(R.id.collectionRecyclerView);

        // Setup Layout Managers
        budgetItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        collectionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create sample data (replace with your actual data)
        List<BudgetItemAdapter.BudgetItem> budgetItems = new ArrayList<>();
        budgetItems.add(new BudgetItemAdapter.BudgetItem("Accommodation", 20000));
        budgetItems.add(new BudgetItemAdapter.BudgetItem("Transportation", 15000));
        budgetItems.add(new BudgetItemAdapter.BudgetItem("Food", 8000));

        List<CollectionAdapter.CollectionItem> collectionItems = new ArrayList<>();
        collectionItems.add(new CollectionAdapter.CollectionItem("Savindu", 5000, "Paid", ""));
        collectionItems.add(new CollectionAdapter.CollectionItem("Alex", 5000, "Pending", ""));

        // Setup Adapters
        budgetItemAdapter = new BudgetItemAdapter(budgetItems);
        collectionAdapter = new CollectionAdapter(collectionItems);

        budgetItemsRecyclerView.setAdapter(budgetItemAdapter);
        collectionRecyclerView.setAdapter(collectionAdapter);

        // Setup click listeners
        view.findViewById(R.id.addBudgetItemButton).setOnClickListener(v -> {
            // Handle add budget item
        });

        view.findViewById(R.id.addBudgetCollectionButton).setOnClickListener(v -> {
            // Handle add collection
        });

        return view;
    }

    // Helper method to update totals
    private void updateTotals() {
        // Calculate and update total budget
        double totalBudget = 0;
        for (BudgetItemAdapter.BudgetItem item : budgetItemAdapter.budgetItems) {
            totalBudget += item.getAmount();
        }

        TextView totalValue = getView().findViewById(R.id.totalValue);
        totalValue.setText(String.format("LKR %,.2f", totalBudget));

        // Calculate and update per person amount
        int personCount = collectionAdapter.collectionItems.size();
        double perPerson = personCount > 0 ? totalBudget / personCount : 0;

        TextView perPersonValue = getView().findViewById(R.id.perPersonValue);
        perPersonValue.setText(String.format("LKR %,.2f", perPerson));

        // Calculate and update total collected
        double totalCollected = 0;
        for (CollectionAdapter.CollectionItem item : collectionAdapter.collectionItems) {
            if ("Paid".equals(item.getStatus())) {
                totalCollected += item.getAmount();
            }
        }

        TextView totalCollectionValue = getView().findViewById(R.id.totalCollectionValue);
        totalCollectionValue.setText(String.format("LKR %,.2f", totalCollected));
    }
}