package com.nerdgeeks.nerdcrict20.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batsman {

    @SerializedName("balls_faced")
    @Expose
    private String ballsFaced;
    @SerializedName("fours")
    @Expose
    private String fours;
    @SerializedName("live_current_name")
    @Expose
    private String liveCurrentName;
    @SerializedName("runs")
    @Expose
    private String runs;
    @SerializedName("sixes")
    @Expose
    private String sixes;
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("old_player_id")
    @Expose
    private String oldPlayerId;

    public String getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(String ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public String getFours() {
        return fours;
    }

    public void setFours(String fours) {
        this.fours = fours;
    }

    public String getLiveCurrentName() {
        return liveCurrentName;
    }

    public void setLiveCurrentName(String liveCurrentName) {
        this.liveCurrentName = liveCurrentName;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getSixes() {
        return sixes;
    }

    public void setSixes(String sixes) {
        this.sixes = sixes;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOldPlayerId() {
        return oldPlayerId;
    }

    public void setOldPlayerId(String oldPlayerId) {
        this.oldPlayerId = oldPlayerId;
    }

}