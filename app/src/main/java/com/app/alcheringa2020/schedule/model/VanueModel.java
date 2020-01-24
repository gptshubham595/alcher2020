package com.app.alcheringa2020.schedule.model;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class VanueModel {
    int id;
    String venue_name;
    ArrayList<EventModel> eventModelArrayList;

    public VanueModel(int id, String venue_name, ArrayList<EventModel> eventModelArrayList) {
        this.id = id;
        this.venue_name = venue_name;
        this.eventModelArrayList = eventModelArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public ArrayList<EventModel> getEventModelArrayList() {
        return eventModelArrayList;
    }

    public void setEventModelArrayList(ArrayList<EventModel> eventModelArrayList) {
        this.eventModelArrayList = eventModelArrayList;
    }
}
