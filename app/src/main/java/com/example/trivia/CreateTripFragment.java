package com.example.trivia;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateTripFragment extends AppCompatActivity {
    private CalendarView calendarView;
    private Button buttonCreateTrip;
    private EditText editTextTripName, editTextPlace;

    private List<Date> selectedDates = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_trip);

        calendarView = findViewById(R.id.calendarView);
        buttonCreateTrip = findViewById(R.id.buttonCreateTrip);
        editTextTripName = findViewById(R.id.editTextTripName);
        editTextPlace = findViewById(R.id.editTextPlace);

        MaterialDatePicker<Pair<Long, Long>> rangeDatePicker = MaterialDatePicker.Builder
                .dateRangePicker()
                .setTitleText("SELECT A DATE")
                .build();

        setupCalendarView();
        setupCreateButton();
    }

    private void setupCalendarView() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                Date selectedDate = calendar.getTime();

                // Check if date is already selected
                boolean dateExists = false;
                for (int i = 0; i < selectedDates.size(); i++) {
                    Date date = selectedDates.get(i);
                    Calendar cal1 = Calendar.getInstance();
                    Calendar cal2 = Calendar.getInstance();
                    cal1.setTime(date);
                    cal2.setTime(selectedDate);

                    boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);

                    if (sameDay) {
                        // Remove date if already selected
                        selectedDates.remove(i);
                        dateExists = true;
                        break;
                    }
                }

                // Add date if not already selected
                if (!dateExists) {
                    selectedDates.add(selectedDate);
                }

                // Update UI to show selected dates
                updateSelectedDatesUI();
            }
        });
    }

    private void updateSelectedDatesUI() {
        StringBuilder datesText = new StringBuilder();
        for (Date date : selectedDates) {
            if (datesText.length() > 0) {
                datesText.append(", ");
            }
            datesText.append(dateFormat.format(date));
        }

        // You could update a TextView to show the selected dates
        // or implement custom drawing on the calendar to highlight selected dates

        // For demonstration, show a toast with selected dates
        Toast.makeText(this, "Selected dates: " + datesText, Toast.LENGTH_SHORT).show();
    }

    private void setupCreateButton() {
        buttonCreateTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tripName = editTextTripName.getText().toString();
                String place = editTextPlace.getText().toString();

                if (tripName.isEmpty()) {
                    Toast.makeText(CreateTripFragment.this, "Please enter a trip name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (place.isEmpty()) {
                    Toast.makeText(CreateTripFragment.this, "Please enter a place", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selectedDates.isEmpty()) {
                    Toast.makeText(CreateTripFragment.this, "Please select dates for your trip", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create the trip with the selected information
                createTrip(tripName, place, selectedDates);
            }
        });
    }

    private void createTrip(String name, String place, List<Date> dates) {
        // Logic to save the trip to your data source
        // ...

        Toast.makeText(this, "Trip created successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}