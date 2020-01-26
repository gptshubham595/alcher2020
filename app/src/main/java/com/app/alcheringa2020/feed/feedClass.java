package com.app.alcheringa2020.feed;

public class feedClass {
    String title,text,image,time;

    public feedClass(String title, String text, String image, String time) {
        this.title = title;
        this.text = text;
        this.image = image;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
