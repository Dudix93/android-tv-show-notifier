
package com.example.android_tv_show_notifier.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvSeriesInfoModel {

    @SerializedName("yearEnd")
    @Expose
    private String yearEnd;
    @SerializedName("creators")
    @Expose
    private String creators;
    @SerializedName("creatorList")
    @Expose
    private List<CreatorModel> creatorList = null;
    @SerializedName("seasons")
    @Expose
    private List<String> seasons = null;

    public String getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(String yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getCreators() {
        return creators;
    }

    public void setCreators(String creators) {
        this.creators = creators;
    }

    public List<CreatorModel> getCreatorList() {
        return creatorList;
    }

    public void setCreatorList(List<CreatorModel> creatorList) {
        this.creatorList = creatorList;
    }

    public List<String> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<String> seasons) {
        this.seasons = seasons;
    }

}
