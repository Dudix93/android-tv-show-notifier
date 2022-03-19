package com.example.android_tv_show_notifier.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NameModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("deathDate")
    @Expose
    private Object deathDate;
    @SerializedName("awards")
    @Expose
    private String awards;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("knownFor")
    @Expose
    private List<KnownForModel> knownFor = null;
    @SerializedName("castMovies")
    @Expose
    private List<CastMovieModel> castMovies = null;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Object getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Object deathDate) {
        this.deathDate = deathDate;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public List<KnownForModel> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<KnownForModel> knownFor) {
        this.knownFor = knownFor;
    }

    public List<CastMovieModel> getCastMovies() {
        return castMovies;
    }

    public void setCastMovies(List<CastMovieModel> castMovies) {
        this.castMovies = castMovies;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
