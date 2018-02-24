package com.nerdgeeks.nerdcrict20.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("fielding")
    @Expose
    private List<Fielding> fielding = null;
    @SerializedName("bowling")
    @Expose
    private List<Bowling> bowling = null;
    @SerializedName("batting")
    @Expose
    private List<Batting> batting = null;
    @SerializedName("matchStarted")
    @Expose
    private Boolean matchStarted;
    @SerializedName("team")
    @Expose
    private List<Team> team = null;

    public List<Fielding> getFielding() {
        return fielding;
    }

    public void setFielding(List<Fielding> fielding) {
        this.fielding = fielding;
    }

    public List<Bowling> getBowling() {
        return bowling;
    }

    public void setBowling(List<Bowling> bowling) {
        this.bowling = bowling;
    }

    public List<Batting> getBatting() {
        return batting;
    }

    public void setBatting(List<Batting> batting) {
        this.batting = batting;
    }

    public Boolean getMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(Boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }

}
