package com.nerdgeeks.nerdcrict20.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bowler {

    @SerializedName("conceded")
    @Expose
    private String conceded;
    @SerializedName("live_current_name")
    @Expose
    private String liveCurrentName;
    @SerializedName("maidens")
    @Expose
    private String maidens;
    @SerializedName("overs")
    @Expose
    private String overs;
    @SerializedName("wickets")
    @Expose
    private String wickets;
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("old_player_id")
    @Expose
    private String oldPlayerId;

    public String getConceded() {
        return conceded;
    }

    public void setConceded(String conceded) {
        this.conceded = conceded;
    }

    public String getLiveCurrentName() {
        return liveCurrentName;
    }

    public void setLiveCurrentName(String liveCurrentName) {
        this.liveCurrentName = liveCurrentName;
    }

    public String getMaidens() {
        return maidens;
    }

    public void setMaidens(String maidens) {
        this.maidens = maidens;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public String getWickets() {
        return wickets;
    }

    public void setWickets(String wickets) {
        this.wickets = wickets;
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
