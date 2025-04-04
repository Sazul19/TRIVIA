package com.example.trivia.model;

import java.util.List;

public  class Trip {
    public String tripName;
    public String place;
    public List<String> dates;

    public Trip() {}

    public Trip(String tripName, String place, List<String> dates) {
        this.tripName = tripName;
        this.place = place;
        this.dates = dates;
    }
}
