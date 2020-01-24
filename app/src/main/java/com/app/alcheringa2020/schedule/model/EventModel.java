package com.app.alcheringa2020.schedule.model;

/**
 * Created by Jiaur Rahman on 07-Jan-20.
 */
public class EventModel {
    int id;
    String event_name,start_time,end_time,venue_name;

    public EventModel(int id, String event_name, String start_time, String end_time, String venue_name) {
        this.id = id;
        this.event_name = event_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.venue_name = venue_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }
}
