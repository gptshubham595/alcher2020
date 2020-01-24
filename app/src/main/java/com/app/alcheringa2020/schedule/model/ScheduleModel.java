package com.app.alcheringa2020.schedule.model;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 07-Jan-20.
 */
public class ScheduleModel {
    int id;
    String day,date;
    ArrayList<VanueModel> vanueModelArrayList;

    public ScheduleModel(int id, String day, String date, ArrayList<VanueModel> vanueModelArrayList) {
        this.id = id;
        this.day = day;
        this.date = date;
        this.vanueModelArrayList = vanueModelArrayList;
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

    public ArrayList<VanueModel> getVanueModelArrayList() {
        return vanueModelArrayList;
    }

    public void setVanueModelArrayList(ArrayList<VanueModel> vanueModelArrayList) {
        this.vanueModelArrayList = vanueModelArrayList;
    }
}
