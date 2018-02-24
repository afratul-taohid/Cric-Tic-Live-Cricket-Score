package com.nerdgeeks.nerdcrict20.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score__ {

    @SerializedName("dismissal-by")
    @Expose
    private DismissalBy dismissalBy;
    @SerializedName("dismissal")
    @Expose
    private String dismissal;
    @SerializedName("SR")
    @Expose
    private String sR;
    @SerializedName("6s")
    @Expose
    private String _6s;
    @SerializedName("4s")
    @Expose
    private String _4s;
    @SerializedName("B")
    @Expose
    private String b;
    @SerializedName("R")
    @Expose
    private String r;
    @SerializedName("dismissal-info")
    @Expose
    private String dismissalInfo;
    @SerializedName("batsman")
    @Expose
    private String batsman;
    @SerializedName("pid")
    @Expose
    private String pid;

    public DismissalBy getDismissalBy() {
        return dismissalBy;
    }

    public void setDismissalBy(DismissalBy dismissalBy) {
        this.dismissalBy = dismissalBy;
    }

    public String getDismissal() {
        return dismissal;
    }

    public void setDismissal(String dismissal) {
        this.dismissal = dismissal;
    }

    public String getSR() {
        return sR;
    }

    public void setSR(String sR) {
        this.sR = sR;
    }

    public String get6s() {
        return _6s;
    }

    public void set6s(String _6s) {
        this._6s = _6s;
    }

    public String get4s() {
        return _4s;
    }

    public void set4s(String _4s) {
        this._4s = _4s;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getDismissalInfo() {
        return dismissalInfo;
    }

    public void setDismissalInfo(String dismissalInfo) {
        this.dismissalInfo = dismissalInfo;
    }

    public String getBatsman() {
        return batsman;
    }

    public void setBatsman(String batsman) {
        this.batsman = batsman;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

}