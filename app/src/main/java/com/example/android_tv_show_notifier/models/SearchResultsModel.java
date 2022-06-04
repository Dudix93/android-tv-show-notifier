package com.example.android_tv_show_notifier.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResultsModel {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("resultType")
    @Expose
    private String resultType;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    public String getId() { return id; }
    public void setId(String value) { this.id = value; }

    public String getResultType() { return resultType; }
    public void setResultType(String value) { this.resultType = value; }

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }
}
