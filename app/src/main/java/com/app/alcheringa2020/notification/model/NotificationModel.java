package com.app.alcheringa2020.notification.model;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
public class NotificationModel {
    int id;
    String day,date;
    ArrayList<NotiDetailModel> notiDetailModelArrayList;

    public NotificationModel(int id, String day, String date, ArrayList<NotiDetailModel> notiDetailModelArrayList) {
        this.id = id;
        this.day = day;
        this.date = date;
        this.notiDetailModelArrayList = notiDetailModelArrayList;
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

    public ArrayList<NotiDetailModel> getNotiDetailModelArrayList() {
        return notiDetailModelArrayList;
    }

    public void setNotiDetailModelArrayList(ArrayList<NotiDetailModel> notiDetailModelArrayList) {
        this.notiDetailModelArrayList = notiDetailModelArrayList;
    }
}
