package com.nerdgeeks.nerdcrict20.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bowling {

    @SerializedName("scores")
    @Expose
    private List<Score_> scores = null;
    @SerializedName("title")
    @Expose
    private String title;

    public List<Score_> getScores() {
        return scores;
    }

    public void setScores(List<Score_> scores) {
        this.scores = scores;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}