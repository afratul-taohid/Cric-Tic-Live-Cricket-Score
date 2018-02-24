package com.nerdgeeks.nerdcrict20.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score_ {

    @SerializedName("Econ")
    @Expose
    private String econ;
    @SerializedName("W")
    @Expose
    private String w;
    @SerializedName("R")
    @Expose
    private String r;
    @SerializedName("M")
    @Expose
    private String m;
    @SerializedName("O")
    @Expose
    private String o;
    @SerializedName("bowler")
    @Expose
    private String bowler;
    @SerializedName("pid")
    @Expose
    private String pid;

    public String getEcon() {
        return econ;
    }

    public void setEcon(String econ) {
        this.econ = econ;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

}