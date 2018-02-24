package com.nerdgeeks.nerdcrict20.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ball {

    @SerializedName("comms_id")
    @Expose
    private String commsId;
    @SerializedName("dismissal")
    @Expose
    private String dismissal;
    @SerializedName("event")
    @Expose
    private String event;
    @SerializedName("innings_number")
    @Expose
    private String inningsNumber;
    @SerializedName("is_tweet")
    @Expose
    private String isTweet;
    @SerializedName("over_number")
    @Expose
    private String overNumber;
    @SerializedName("overs_actual")
    @Expose
    private String oversActual;
    @SerializedName("overs_unique")
    @Expose
    private String oversUnique;
    @SerializedName("players")
    @Expose
    private String players;
    @SerializedName("speed_kph")
    @Expose
    private String speedKph;
    @SerializedName("speed_mph")
    @Expose
    private String speedMph;
    @SerializedName("text")
    @Expose
    private String text;

    public String getCommsId() {
        return commsId;
    }

    public void setCommsId(String commsId) {
        this.commsId = commsId;
    }

    public String getDismissal() {
        return dismissal;
    }

    public void setDismissal(String dismissal) {
        this.dismissal = dismissal;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getInningsNumber() {
        return inningsNumber;
    }

    public void setInningsNumber(String inningsNumber) {
        this.inningsNumber = inningsNumber;
    }

    public String getIsTweet() {
        return isTweet;
    }

    public void setIsTweet(String isTweet) {
        this.isTweet = isTweet;
    }

    public String getOverNumber() {
        return overNumber;
    }

    public void setOverNumber(String overNumber) {
        this.overNumber = overNumber;
    }

    public String getOversActual() {
        return oversActual;
    }

    public void setOversActual(String oversActual) {
        this.oversActual = oversActual;
    }

    public String getOversUnique() {
        return oversUnique;
    }

    public void setOversUnique(String oversUnique) {
        this.oversUnique = oversUnique;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getSpeedKph() {
        return speedKph;
    }

    public void setSpeedKph(String speedKph) {
        this.speedKph = speedKph;
    }

    public String getSpeedMph() {
        return speedMph;
    }

    public void setSpeedMph(String speedMph) {
        this.speedMph = speedMph;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}