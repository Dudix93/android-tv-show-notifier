
package com.example.android_tv_show_notifier.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TitleModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("originalTitle")
    @Expose
    private String originalTitle;
    @SerializedName("fullTitle")
    @Expose
    private String fullTitle;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("runtimeMins")
    @Expose
    private Object runtimeMins;
    @SerializedName("runtimeStr")
    @Expose
    private Object runtimeStr;
    @SerializedName("plot")
    @Expose
    private String plot;
    @SerializedName("plotLocal")
    @Expose
    private String plotLocal;
    @SerializedName("plotLocalIsRtl")
    @Expose
    private Boolean plotLocalIsRtl;
    @SerializedName("awards")
    @Expose
    private String awards;
    @SerializedName("directors")
    @Expose
    private String directors;
    @SerializedName("directorList")
    @Expose
    private List<Object> directorList = null;
    @SerializedName("writers")
    @Expose
    private String writers;
    @SerializedName("writerList")
    @Expose
    private List<Object> writerList = null;
    @SerializedName("stars")
    @Expose
    private String stars;
    @SerializedName("starList")
    @Expose
    private List<StarModel> starList = null;
    @SerializedName("actorList")
    @Expose
    private List<ActorModel> actorList = null;
    @SerializedName("fullCast")
    @Expose
    private Object fullCast;
    @SerializedName("genres")
    @Expose
    private String genres;
    @SerializedName("genreList")
    @Expose
    private List<GenreModel> genreList = null;
    @SerializedName("companies")
    @Expose
    private String companies;
    @SerializedName("companyList")
    @Expose
    private List<CompanyModel> companyList = null;
    @SerializedName("countries")
    @Expose
    private String countries;
    @SerializedName("countryList")
    @Expose
    private List<CountryModel> countryList = null;
    @SerializedName("languages")
    @Expose
    private String languages;
    @SerializedName("languageList")
    @Expose
    private List<LanguageModel> languageList = null;
    @SerializedName("contentRating")
    @Expose
    private String contentRating;
    @SerializedName("imDbRating")
    @Expose
    private String imDbRating;
    @SerializedName("imDbRatingVotes")
    @Expose
    private String imDbRatingVotes;
    @SerializedName("metacriticRating")
    @Expose
    private String metacriticRating;
    @SerializedName("ratings")
    @Expose
    private Object ratings;
    @SerializedName("wikipedia")
    @Expose
    private Object wikipedia;
    @SerializedName("posters")
    @Expose
    private Object posters;
    @SerializedName("images")
    @Expose
    private Object images;
    @SerializedName("trailer")
    @Expose
    private Object trailer;
    @SerializedName("boxOffice")
    @Expose
    private BoxOfficeModel boxOffice;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("keywords")
    @Expose
    private String keywords;
    @SerializedName("keywordList")
    @Expose
    private List<String> keywordList = null;
    @SerializedName("similars")
    @Expose
    private List<SimilarModel> similars = null;
    @SerializedName("tvSeriesInfo")
    @Expose
    private TvSeriesInfoModel tvSeriesInfo;
    @SerializedName("tvEpisodeInfo")
    @Expose
    private Object tvEpisodeInfo;
    @SerializedName("errorMessage")
    @Expose
    private Object errorMessage;

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

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Object getRuntimeMins() {
        return runtimeMins;
    }

    public void setRuntimeMins(Object runtimeMins) {
        this.runtimeMins = runtimeMins;
    }

    public Object getRuntimeStr() {
        return runtimeStr;
    }

    public void setRuntimeStr(Object runtimeStr) {
        this.runtimeStr = runtimeStr;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPlotLocal() {
        return plotLocal;
    }

    public void setPlotLocal(String plotLocal) {
        this.plotLocal = plotLocal;
    }

    public Boolean getPlotLocalIsRtl() {
        return plotLocalIsRtl;
    }

    public void setPlotLocalIsRtl(Boolean plotLocalIsRtl) {
        this.plotLocalIsRtl = plotLocalIsRtl;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public List<Object> getDirectorList() {
        return directorList;
    }

    public void setDirectorList(List<Object> directorList) {
        this.directorList = directorList;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public List<Object> getWriterList() {
        return writerList;
    }

    public void setWriterList(List<Object> writerList) {
        this.writerList = writerList;
    }

    public String getStarModels() {
        return stars;
    }

    public void setStarModels(String stars) {
        this.stars = stars;
    }

    public List<StarModel> getStarModelList() {
        return starList;
    }

    public void setStarModelList(List<StarModel> starList) {
        this.starList = starList;
    }

    public List<ActorModel> getActorList() {
        return actorList;
    }

    public void setActorList(List<ActorModel> actorList) {
        this.actorList = actorList;
    }

    public Object getFullCast() {
        return fullCast;
    }

    public void setFullCast(Object fullCast) {
        this.fullCast = fullCast;
    }

    public String getGenreModels() {
        return genres;
    }

    public void setGenreModels(String genres) {
        this.genres = genres;
    }

    public List<GenreModel> getGenreModelList() {
        return genreList;
    }

    public void setGenreModelList(List<GenreModel> genreList) {
        this.genreList = genreList;
    }

    public String getCompanies() {
        return companies;
    }

    public void setCompanies(String companies) {
        this.companies = companies;
    }

    public List<CompanyModel> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanyModel> companyList) {
        this.companyList = companyList;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public List<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public List<LanguageModel> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<LanguageModel> languageList) {
        this.languageList = languageList;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    public String getImDbRatingVotes() {
        return imDbRatingVotes;
    }

    public void setImDbRatingVotes(String imDbRatingVotes) {
        this.imDbRatingVotes = imDbRatingVotes;
    }

    public String getMetacriticRating() {
        return metacriticRating;
    }

    public void setMetacriticRating(String metacriticRating) {
        this.metacriticRating = metacriticRating;
    }

    public Object getRatings() {
        return ratings;
    }

    public void setRatings(Object ratings) {
        this.ratings = ratings;
    }

    public Object getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(Object wikipedia) {
        this.wikipedia = wikipedia;
    }

    public Object getPosters() {
        return posters;
    }

    public void setPosters(Object posters) {
        this.posters = posters;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public Object getTrailer() {
        return trailer;
    }

    public void setTrailer(Object trailer) {
        this.trailer = trailer;
    }

    public BoxOfficeModel getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(BoxOfficeModel boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }

    public List<SimilarModel> getSimilars() {
        return similars;
    }

    public void setSimilars(List<SimilarModel> similars) {
        this.similars = similars;
    }

    public TvSeriesInfoModel getTvSeriesInfo() {
        return tvSeriesInfo;
    }

    public void setTvSeriesInfo(TvSeriesInfoModel tvSeriesInfo) {
        this.tvSeriesInfo = tvSeriesInfo;
    }

    public Object getTvEpisodeInfo() {
        return tvEpisodeInfo;
    }

    public void setTvEpisodeInfo(Object tvEpisodeInfo) {
        this.tvEpisodeInfo = tvEpisodeInfo;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

}
