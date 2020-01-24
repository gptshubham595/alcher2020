package com.app.alcheringa2020.events.model;

/**
 * Created by Jiaur Rahman on 03-Jan-20.
 */
public class JudgeModel {
    int judgeId;
    String judgeCriteria;

    public JudgeModel(int judgeId, String judgeCriteria) {
        this.judgeId = judgeId;
        this.judgeCriteria = judgeCriteria;
    }

    public int getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(int judgeId) {
        this.judgeId = judgeId;
    }

    public String getJudgeCriteria() {
        return judgeCriteria;
    }

    public void setJudgeCriteria(String judgeCriteria) {
        this.judgeCriteria = judgeCriteria;
    }
}
