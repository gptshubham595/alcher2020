package com.app.alcheringa2020.notification.model;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
public class NotiDetailModel {
    int id;
    String message,title;

    public NotiDetailModel(int id, String message,String title) {
        this.id = id;
        this.message = message;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
