package com.app.alcheringa2020.events.model;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 03-Jan-20.
 */
public  class ProgrammeModel {
    int proId;
    String proCategory,proImage;
    ArrayList<ItemModel> itemModelArrayList;

    public ProgrammeModel(int proId, String proCategory, String proImage, ArrayList<ItemModel> itemModelArrayList) {
        this.proId = proId;
        this.proCategory = proCategory;
        this.proImage = proImage;
        this.itemModelArrayList = itemModelArrayList;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProCategory() {
        return proCategory;
    }

    public void setProCategory(String proCategory) {
        this.proCategory = proCategory;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public ArrayList<ItemModel> getItemModelArrayList() {
        return itemModelArrayList;
    }

    public void setItemModelArrayList(ArrayList<ItemModel> itemModelArrayList) {
        this.itemModelArrayList = itemModelArrayList;
    }
}
