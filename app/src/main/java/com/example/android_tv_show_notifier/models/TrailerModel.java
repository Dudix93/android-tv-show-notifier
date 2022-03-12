package com.example.android_tv_show_notifier.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class TrailerModel {

    @SerializedName("imDbId")
    @Expose
    private String imDbId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("fullTitle")
    @Expose
    private String fullTitle;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("videoId")
    @Expose
    private String videoId;
    @SerializedName("videoTitle")
    @Expose
    private String videoTitle;
    @SerializedName("videoDescription")
    @Expose
    private String videoDescription;
    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;
    @SerializedName("uploadDate")
    @Expose
    private Object uploadDate;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("linkEmbed")
    @Expose
    private String linkEmbed;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    public String getImDbId() {
        return imDbId;
    }

    public void setImDbId(String imDbId) {
        this.imDbId = imDbId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Object getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Object uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkEmbed() {
        return linkEmbed;
    }

    public void setLinkEmbed(String linkEmbed) {
        this.linkEmbed = linkEmbed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}