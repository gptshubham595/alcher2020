package com.app.alcheringa2020.schedule.model;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class MyScheduleEventModel {
    int id;
    String event_name,venue,start_time,end_time;

    public MyScheduleEventModel(int id, String event_name, String venue, String start_time, String end_time) {
        this.id = id;
        this.event_name = event_name;
        this.venue = venue;
        this.start_time = start_time;
        this.end_time = end_time;
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
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
}
