package com.example.android_tv_show_notifier;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MostPopularDataModel {

    @SerializedName("items")
    @Expose
    private List<MostPopularDataDetailModel> items = null;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    public List<MostPopularDataDetailModel> getItems() {
        return items;
    }

    public void setItems(List<MostPopularDataDetailModel> items) {
        this.items = items;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
