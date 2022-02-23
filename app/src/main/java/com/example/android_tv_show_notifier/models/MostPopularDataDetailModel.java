package com.example.android_tv_show_notifier.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostPopularDataDetailModel {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("rank")
    @Expose
    private String rank;

    @SerializedName("rankUpDown")
    @Expose
    private String rankUpDown;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("fullTitle")
    @Expose
    private String fullTitle;

    @SerializedName("year")
    @Expose
    private String year;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("crew")
    @Expose
    private String crew;

    @SerializedName("imDbRating")
    @Expose
    private String imDbRating;

    @SerializedName("imDbRatingCount")
    @Expose
    private String imDbRatingCount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankUpDown() {
        return rankUpDown;
    }

    public void setRankUpDown(String rankUpDown) {
        this.rankUpDown = rankUpDown;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    public String getImDbRatingCount() {
        return imDbRatingCount;
    }

    public void setImDbRatingCount(String imDbRatingCount) {
        this.imDbRatingCount = imDbRatingCount;
    }
}
