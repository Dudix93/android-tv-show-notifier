package com.example.android_tv_show_notifier.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewMovieDataModel {

    @SerializedName("items")
    @Expose
    private List<NewMovieDataDetailModel> items = null;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    public List<NewMovieDataDetailModel> getItems() {
        return items;
    }

    public void setItems(List<NewMovieDataDetailModel> items) {
        this.items = items;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
