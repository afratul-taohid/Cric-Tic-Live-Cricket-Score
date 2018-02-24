package com.nerdgeeks.nerdcrict20.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("unique_id")
    @Expose
    private Integer uniqueId;
    @SerializedName("squad")
    @Expose
    private Boolean squad;
    @SerializedName("dateTimeGMT")
    @Expose
    private String dateTimeGMT;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("team-2")
    @Expose
    private String team2;
    @SerializedName("team-1")
    @Expose
    private String team1;
    @SerializedName("matchStarted")
    @Expose
    private Boolean matchStarted;
    @SerializedName("toss_winner_team")
    @Expose
    private Object tossWinnerTeam;
    @SerializedName("winner_team")
    @Expose
    private Object winnerTeam;
    @SerializedName("cancelledMatch")
    @Expose
    private Boolean cancelledMatch;

    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Boolean getSquad() {
        return squad;
    }

    public void setSquad(Boolean squad) {
        this.squad = squad;
    }

    public String getDateTimeGMT() {
        return dateTimeGMT;
    }

    public void setDateTimeGMT(String dateTimeGMT) {
        this.dateTimeGMT = dateTimeGMT;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public Boolean getMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(Boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    public Object getTossWinnerTeam() {
        return tossWinnerTeam;
    }

    public void setTossWinnerTeam(Object tossWinnerTeam) {
        this.tossWinnerTeam = tossWinnerTeam;
    }

    public Object getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Object winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Boolean getCancelledMatch() {
        return cancelledMatch;
    }

    public void setCancelledMatch(Boolean cancelledMatch) {
        this.cancelledMatch = cancelledMatch;
    }

}