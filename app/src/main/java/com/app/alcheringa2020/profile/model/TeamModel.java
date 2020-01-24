package com.app.alcheringa2020.profile.model;

/**
 * Created by Jiaur Rahman on 10-Jan-20.
 */
public class TeamModel {
    int id;
    String name,code,image;

    public TeamModel(int id, String name, String code, String image) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
