package com.nerdgeeks.nerdcrict20.helper;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("batsmen_in_side")
    @Expose
    private String batsmenInSide;
    @SerializedName("content_id")
    @Expose
    private String contentId;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("fielders_in_side")
    @Expose
    private String fieldersInSide;
    @SerializedName("logo_alt_id")
    @Expose
    private String logoAltId;
    @SerializedName("logo_espncdn")
    @Expose
    private String logoEspncdn;
    @SerializedName("logo_height")
    @Expose
    private String logoHeight;
    @SerializedName("logo_image_height")
    @Expose
    private String logoImageHeight;
    @SerializedName("logo_image_path")
    @Expose
    private String logoImagePath;
    @SerializedName("logo_image_width")
    @Expose
    private String logoImageWidth;
    @SerializedName("logo_object_id")
    @Expose
    private String logoObjectId;
    @SerializedName("logo_path")
    @Expose
    private String logoPath;
    @SerializedName("logo_width")
    @Expose
    private String logoWidth;
    @SerializedName("object_id")
    @Expose
    private String objectId;
    @SerializedName("player")
    @Expose
    private List<Player> player = null;
    @SerializedName("players_in_side")
    @Expose
    private String playersInSide;
    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("team_abbreviation")
    @Expose
    private String teamAbbreviation;
    @SerializedName("team_filename")
    @Expose
    private String teamFilename;
    @SerializedName("team_general_name")
    @Expose
    private String teamGeneralName;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("team_short_name")
    @Expose
    private String teamShortName;
    @SerializedName("url_component")
    @Expose
    private String urlComponent;

    public String getBatsmenInSide() {
        return batsmenInSide;
    }

    public void setBatsmenInSide(String batsmenInSide) {
        this.batsmenInSide = batsmenInSide;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getFieldersInSide() {
        return fieldersInSide;
    }

    public void setFieldersInSide(String fieldersInSide) {
        this.fieldersInSide = fieldersInSide;
    }

    public String getLogoAltId() {
        return logoAltId;
    }

    public void setLogoAltId(String logoAltId) {
        this.logoAltId = logoAltId;
    }

    public String getLogoEspncdn() {
        return logoEspncdn;
    }

    public void setLogoEspncdn(String logoEspncdn) {
        this.logoEspncdn = logoEspncdn;
    }

    public String getLogoHeight() {
        return logoHeight;
    }

    public void setLogoHeight(String logoHeight) {
        this.logoHeight = logoHeight;
    }

    public String getLogoImageHeight() {
        return logoImageHeight;
    }

    public void setLogoImageHeight(String logoImageHeight) {
        this.logoImageHeight = logoImageHeight;
    }

    public String getLogoImagePath() {
        return logoImagePath;
    }

    public void setLogoImagePath(String logoImagePath) {
        this.logoImagePath = logoImagePath;
    }

    public String getLogoImageWidth() {
        return logoImageWidth;
    }

    public void setLogoImageWidth(String logoImageWidth) {
        this.logoImageWidth = logoImageWidth;
    }

    public String getLogoObjectId() {
        return logoObjectId;
    }

    public void setLogoObjectId(String logoObjectId) {
        this.logoObjectId = logoObjectId;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoWidth() {
        return logoWidth;
    }

    public void setLogoWidth(String logoWidth) {
        this.logoWidth = logoWidth;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public String getPlayersInSide() {
        return playersInSide;
    }

    public void setPlayersInSide(String playersInSide) {
        this.playersInSide = playersInSide;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTeamAbbreviation() {
        return teamAbbreviation;
    }

    public void setTeamAbbreviation(String teamAbbreviation) {
        this.teamAbbreviation = teamAbbreviation;
    }

    public String getTeamFilename() {
        return teamFilename;
    }

    public void setTeamFilename(String teamFilename) {
        this.teamFilename = teamFilename;
    }

    public String getTeamGeneralName() {
        return teamGeneralName;
    }

    public void setTeamGeneralName(String teamGeneralName) {
        this.teamGeneralName = teamGeneralName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamShortName() {
        return teamShortName;
    }

    public void setTeamShortName(String teamShortName) {
        this.teamShortName = teamShortName;
    }

    public String getUrlComponent() {
        return urlComponent;
    }

    public void setUrlComponent(String urlComponent) {
        this.urlComponent = urlComponent;
    }

}