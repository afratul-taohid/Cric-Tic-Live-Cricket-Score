package com.nerdgeeks.nerdcrict20.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("stumped")
    @Expose
    private Integer stumped;
    @SerializedName("bowled")
    @Expose
    private Integer bowled;
    @SerializedName("lbw")
    @Expose
    private Integer lbw;
    @SerializedName("catch")
    @Expose
    private Integer _catch;
    @SerializedName("pid")
    @Expose
    private String pid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStumped() {
        return stumped;
    }

    public void setStumped(Integer stumped) {
        this.stumped = stumped;
    }

    public Integer getBowled() {
        return bowled;
    }

    public void setBowled(Integer bowled) {
        this.bowled = bowled;
    }

    public Integer getLbw() {
        return lbw;
    }

    public void setLbw(Integer lbw) {
        this.lbw = lbw;
    }

    public Integer getCatch() {
        return _catch;
    }

    public void setCatch(Integer _catch) {
        this._catch = _catch;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

}