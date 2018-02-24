package com.nerdgeeks.nerdcrict20.helper;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nerdgeeks.nerdcrict20.models.Ball;
import com.nerdgeeks.nerdcrict20.models.Batsman;
import com.nerdgeeks.nerdcrict20.models.Bowler;

public class Datum {

    @SerializedName("ball")
    @Expose
    private List<Ball> ball = null;
    @SerializedName("innings_number")
    @Expose
    private String inningsNumber;
    @SerializedName("over_number")
    @Expose
    private String overNumber;
    @SerializedName("batsman")
    @Expose
    private List<Batsman> batsman = null;
    @SerializedName("bowler")
    @Expose
    private List<Bowler> bowler = null;
    @SerializedName("event_string")
    @Expose
    private String eventString;
    @SerializedName("over_complete")
    @Expose
    private String overComplete;
    @SerializedName("required_string")
    @Expose
    private String requiredString;
    @SerializedName("runs")
    @Expose
    private String runs;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("wickets")
    @Expose
    private String wickets;

    public List<Ball> getBall() {
        return ball;
    }

    public void setBall(List<Ball> ball) {
        this.ball = ball;
    }

    public String getInningsNumber() {
        return inningsNumber;
    }

    public void setInningsNumber(String inningsNumber) {
        this.inningsNumber = inningsNumber;
    }

    public String getOverNumber() {
        return overNumber;
    }

    public void setOverNumber(String overNumber) {
        this.overNumber = overNumber;
    }

    public List<Batsman> getBatsman() {
        return batsman;
    }

    public void setBatsman(List<Batsman> batsman) {
        this.batsman = batsman;
    }

    public List<Bowler> getBowler() {
        return bowler;
    }

    public void setBowler(List<Bowler> bowler) {
        this.bowler = bowler;
    }

    public String getEventString() {
        return eventString;
    }

    public void setEventString(String eventString) {
        this.eventString = eventString;
    }

    public String getOverComplete() {
        return overComplete;
    }

    public void setOverComplete(String overComplete) {
        this.overComplete = overComplete;
    }

    public String getRequiredString() {
        return requiredString;
    }

    public void setRequiredString(String requiredString) {
        this.requiredString = requiredString;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getWickets() {
        return wickets;
    }

    public void setWickets(String wickets) {
        this.wickets = wickets;
    }

}
