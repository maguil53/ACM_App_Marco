package com.bignerdranch.android.demoapp.model.mlh;

import com.bignerdranch.android.demoapp.model.mlh.Event;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hackathons {

    @SerializedName("array")
    List<Event> events;

    public List<Event> getEvents() {
        return events;
    }
}

