package com.example.trivia;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import com.google.android.material.datepicker.MaterialDatePicker;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class TripPlannerFragment extends Fragment {

    private EditText etTripName, etPlace;
    private Button btnSelectDates, btnCreateTrip;
    private TextView tvSelectedDates;
    private List<String> selectedDates = new ArrayList<>();
    //private DatabaseReference tripsRef;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etTripName = view.findViewById(R.id.editTextTripName);
        etPlace = view.findViewById(R.id.editTextPlace);
        btnSelectDates = view.findViewById(R.id.buttonSelectDates);
        btnCreateTrip = view.findViewById(R.id.buttonCreateTrip);
        tvSelectedDates = view.findViewById(R.id.buttonSelectDates);

     //   tripsRef = FirebaseDatabase.getInstance().getReference("trips");

        btnSelectDates.setOnClickListener(v -> openDatePicker());

        btnCreateTrip.setOnClickListener(v -> saveTripToFirebase());
    }

    private void openDatePicker() {
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        MaterialDatePicker<?> picker = builder.build();

        picker.addOnPositiveButtonClickListener(selection -> {
            selectedDates.clear();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            if (selection instanceof android.util.Pair) {
                android.util.Pair<Long, Long> dateRange = (android.util.Pair<Long, Long>) selection;
                selectedDates.add(sdf.format(dateRange.first));
                selectedDates.add(sdf.format(dateRange.second));

                tvSelectedDates.setText("Selected Dates: " + selectedDates);
            }
        });

        picker.show(getParentFragmentManager(), "DATE_PICKER");
    }

    private void saveTripToFirebase() {
        String tripName = etTripName.getText().toString().trim();
        String place = etPlace.getText().toString().trim();

        if (tripName.isEmpty() || place.isEmpty() || selectedDates.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all details and select dates", Toast.LENGTH_SHORT).show();
            return;
        }

//        String tripId = tripsRef.push().getKey();
//        Trip trip = new Trip(tripName, place, selectedDates);
//        tripsRef.child(tripId).setValue(trip)
//                .addOnSuccessListener(aVoid -> Toast.makeText(getContext(), "Trip saved!", Toast.LENGTH_SHORT).show())
//                .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to save trip", Toast.LENGTH_SHORT).show());
    }}


