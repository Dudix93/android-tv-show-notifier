package com.example.android_tv_show_notifier.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchDataModel {

        @SerializedName("string")
        @Expose
        private String string;

        @SerializedName("expression")
        @Expose
        private String expression;

        @SerializedName("results")
        @Expose
        private List<SearchResultsModel> results = null;

        @SerializedName("errorMessage")
        @Expose
        private String errorMessage;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<SearchResultsModel> getResults() {
        return results;
    }

    public void setResults(List<SearchResultsModel> results) {
        this.results = results;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
