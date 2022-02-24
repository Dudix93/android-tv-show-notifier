
package com.example.android_tv_show_notifier.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimilarModel {

    @SerializedName("id")
    @Expose
    private String id;
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
    @SerializedName("plot")
    @Expose
    private String plot;
    @SerializedName("directors")
    @Expose
    private String directors;
    @SerializedName("stars")
    @Expose
    private String stars;
    @SerializedName("genres")
    @Expose
    private String genres;
    @SerializedName("imDbRating")
    @Expose
    private String imDbRating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

}
