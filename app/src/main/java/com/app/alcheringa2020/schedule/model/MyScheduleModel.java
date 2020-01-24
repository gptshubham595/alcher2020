package com.app.alcheringa2020.schedule.model;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class MyScheduleModel {
    int id;
    String day,date;
    ArrayList<MyScheduleEventModel> eventModelArrayList;

    public MyScheduleModel(int id, String day, String date, ArrayList<MyScheduleEventModel> eventModelArrayList) {
        this.id = id;
        this.day = day;
        this.date = date;
        this.eventModelArrayList = eventModelArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<MyScheduleEventModel> getEventModelArrayList() {
        return eventModelArrayList;
    }

    public void setEventModelArrayList(ArrayList<MyScheduleEventModel> eventModelArrayList) {
        this.eventModelArrayList = eventModelArrayList;
    }
}
