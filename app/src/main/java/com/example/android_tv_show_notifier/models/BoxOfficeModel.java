
package com.example.android_tv_show_notifier.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoxOfficeModel {

    @SerializedName("budget")
    @Expose
    private String budget;
    @SerializedName("openingWeekendUSA")
    @Expose
    private String openingWeekendUSA;
    @SerializedName("grossUSA")
    @Expose
    private String grossUSA;
    @SerializedName("cumulativeWorldwideGross")
    @Expose
    private String cumulativeWorldwideGross;

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getOpeningWeekendUSA() {
        return openingWeekendUSA;
    }

    public void setOpeningWeekendUSA(String openingWeekendUSA) {
        this.openingWeekendUSA = openingWeekendUSA;
    }

    public String getGrossUSA() {
        return grossUSA;
    }

    public void setGrossUSA(String grossUSA) {
        this.grossUSA = grossUSA;
    }

    public String getCumulativeWorldwideGross() {
        return cumulativeWorldwideGross;
    }

    public void setCumulativeWorldwideGross(String cumulativeWorldwideGross) {
        this.cumulativeWorldwideGross = cumulativeWorldwideGross;
    }

}
