package com.app.alcheringa2020.events.model;

/**
 * Created by Jiaur Rahman on 03-Jan-20.
 */
public class RuleModel {
    int ruleId;
    String rule;

    public RuleModel(int ruleId, String rule) {
        this.ruleId = ruleId;
        this.rule = rule;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
