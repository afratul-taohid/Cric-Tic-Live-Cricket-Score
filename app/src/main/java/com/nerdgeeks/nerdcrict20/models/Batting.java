package com.nerdgeeks.nerdcrict20.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batting {

    @SerializedName("scores")
    @Expose
    private List<Score__> scores = null;
    @SerializedName("title")
    @Expose
    private String title;

    public List<Score__> getScores() {
        return scores;
    }

    public void setScores(List<Score__> scores) {
        this.scores = scores;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
