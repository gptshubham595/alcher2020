package com.app.alcheringa2020.notification.model;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
public class NotiDetailModel {
    int id;
    String message;

    public NotiDetailModel(int id, String message) {
        this.id = id;
        this.message = message;
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
}
