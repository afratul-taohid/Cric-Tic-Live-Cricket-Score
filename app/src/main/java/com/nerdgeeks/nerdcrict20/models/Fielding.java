package com.nerdgeeks.nerdcrict20.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fielding {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("scores")
    @Expose
    private List<Score> scores = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

}