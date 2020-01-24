package com.app.alcheringa2020.events.model;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 03-Jan-20.
 */
public class ItemModel {
    int itemId;
    String item,itemImage,itemCompetition,itemBounty,itemDescription,pre_header,pre_theme,final_header,final_theme;
    ArrayList<RuleModel> ruleModelArrayList;
    ArrayList<JudgeModel> judgeModelArrayList;

    public ItemModel(int itemId, String item, String itemImage, String itemCompetition, String itemBounty, String itemDescription, String pre_header, String pre_theme, String final_header, String final_theme, ArrayList<RuleModel> ruleModelArrayList, ArrayList<JudgeModel> judgeModelArrayList) {
        this.itemId = itemId;
        this.item = item;
        this.itemImage = itemImage;
        this.itemCompetition = itemCompetition;
        this.itemBounty = itemBounty;
        this.itemDescription = itemDescription;
        this.pre_header = pre_header;
        this.pre_theme = pre_theme;
        this.final_header = final_header;
        this.final_theme = final_theme;
        this.ruleModelArrayList = ruleModelArrayList;
        this.judgeModelArrayList = judgeModelArrayList;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemCompetition() {
        return itemCompetition;
    }

    public void setItemCompetition(String itemCompetition) {
        this.itemCompetition = itemCompetition;
    }

    public String getItemBounty() {
        return itemBounty;
    }

    public void setItemBounty(String itemBounty) {
        this.itemBounty = itemBounty;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getPre_header() {
        return pre_header;
    }

    public void setPre_header(String pre_header) {
        this.pre_header = pre_header;
    }

    public String getPre_theme() {
        return pre_theme;
    }

    public void setPre_theme(String pre_theme) {
        this.pre_theme = pre_theme;
    }

    public String getFinal_header() {
        return final_header;
    }

    public void setFinal_header(String final_header) {
        this.final_header = final_header;
    }

    public String getFinal_theme() {
        return final_theme;
    }

    public void setFinal_theme(String final_theme) {
        this.final_theme = final_theme;
    }

    public ArrayList<RuleModel> getRuleModelArrayList() {
        return ruleModelArrayList;
    }

    public void setRuleModelArrayList(ArrayList<RuleModel> ruleModelArrayList) {
        this.ruleModelArrayList = ruleModelArrayList;
    }

    public ArrayList<JudgeModel> getJudgeModelArrayList() {
        return judgeModelArrayList;
    }

    public void setJudgeModelArrayList(ArrayList<JudgeModel> judgeModelArrayList) {
        this.judgeModelArrayList = judgeModelArrayList;
    }
}
