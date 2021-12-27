package it.sal.disco.unimib.filmreviewer.customObj;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.versionedparcelable.ParcelField;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import it.sal.disco.unimib.filmreviewer.utils.Converters;

@Entity
public class Movie {
    //Implementation 1
    @PrimaryKey
    @NonNull
    private String id;
    private int rank;
    private String title;
    private String fullTitle;
    private String year;
    private String image;
    private String crew;
    private String imDbRating;
    private String imDbRatingCount;

    //Implementation 2
    private String description;
    private String desc;

    //Implementation P
    private float private_stars;
    private String private_desc;
    private boolean private_fav;

    //Implementation 3
    private String originalTitle;
    private String type;
    private String releaseDate;
    private String runtimeStr;
    private String plot;
    private String plotLocal;
    private String plotLocalIsRtl;
    private String awards;
    private String directors;
    private String writers;
    private String stars;
    @SerializedName("actorList")
    private List<Actor> actorList;
    private String genres;
    private String companies;
    private String countries;
    private String languages;
    @SerializedName("posters")
    private PosterBox posters;
    private String metacriticRating;


    public Movie(@NonNull String id, int rank, String title, String fullTitle, String year, String image, String crew, String imDbRating, String imDbRatingCount, String description, String desc, float private_stars, String private_desc, boolean private_fav, String originalTitle, String type, String releaseDate, String runtimeStr, String plot, String plotLocal, String plotLocalIsRtl, String awards, String directors, String writers, String stars, List<Actor> actorList, String genres, String companies, String countries, String languages, PosterBox posters, String metacriticRating) {
        this.id = id;
        this.rank = rank;
        this.title = title;
        this.fullTitle = fullTitle;
        this.year = year;
        this.image = image;
        this.crew = crew;
        this.imDbRating = imDbRating;
        this.imDbRatingCount = imDbRatingCount;
        this.description = description;
        this.desc = desc;
        this.private_stars = private_stars;
        this.private_desc = private_desc;
        this.private_fav = private_fav;
        this.originalTitle = originalTitle;
        this.type = type;
        this.releaseDate = releaseDate;
        this.runtimeStr = runtimeStr;
        this.plot = plot;
        this.plotLocal = plotLocal;
        this.plotLocalIsRtl = plotLocalIsRtl;
        this.awards = awards;
        this.directors = directors;
        this.writers = writers;
        this.stars = stars;
        this.actorList = actorList;
        this.genres = genres;
        this.companies = companies;
        this.countries = countries;
        this.languages = languages;
        this.posters = posters;
        this.metacriticRating = metacriticRating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    public String getImDbRatingCount() {
        return imDbRatingCount;
    }

    public void setImDbRatingCount(String imDbRatingCount) {
        this.imDbRatingCount = imDbRatingCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRuntimeStr() {
        return runtimeStr;
    }

    public void setRuntimeStr(String runtimeStr) {
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

    public String getPlotLocalIsRtl() {
        return plotLocalIsRtl;
    }

    public void setPlotLocalIsRtl(String plotLocalIsRtl) {
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

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCompanies() {
        return companies;
    }

    public void setCompanies(String companies) {
        this.companies = companies;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public PosterBox getPosters() {
        return posters;
    }

    public void setPosters(PosterBox posterBox) {
        this.posters = posterBox;
    }

    public String getMetacriticRating() {
        return metacriticRating;
    }

    public void setMetacriticRating(String metacriticRating) {
        this.metacriticRating = metacriticRating;
    }

    public String getPrivate_desc() {
        return private_desc;
    }

    public void setPrivate_desc(String private_desc) {
        this.private_desc = private_desc;
    }

    public void setPrivate_stars(float private_stars) {
        this.private_stars = private_stars;
    }

    public float getPrivate_stars() {
        return private_stars;
    }

    public boolean isPrivate_fav() {
        return private_fav;
    }

    public void setPrivate_fav(boolean private_fav) {
        this.private_fav = private_fav;
    }

    //Personali
    public String getTitle2() {
        if(year!=null && !year.isEmpty()){
            return year;
        }else{
            return description;
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", rank=" + rank +
                ", title='" + title + '\'' +
                ", fullTitle='" + fullTitle + '\'' +
                ", year='" + year + '\'' +
                ", image='" + image + '\'' +
                ", crew='" + crew + '\'' +
                ", imDbRating='" + imDbRating + '\'' +
                ", imDbRatingCount='" + imDbRatingCount + '\'' +
                ", description='" + description + '\'' +
                ", desc='" + desc + '\'' +
                ", private_stars=" + private_stars +
                ", private_desc='" + private_desc + '\'' +
                ", private_fav=" + private_fav +
                ", originalTitle='" + originalTitle + '\'' +
                ", type='" + type + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", runtimeStr='" + runtimeStr + '\'' +
                ", plot='" + plot + '\'' +
                ", plotLocal='" + plotLocal + '\'' +
                ", plotLocalIsRtl='" + plotLocalIsRtl + '\'' +
                ", awards='" + awards + '\'' +
                ", directors='" + directors + '\'' +
                ", writers='" + writers + '\'' +
                ", stars='" + stars + '\'' +
                ", actorList=" + actorList +
                ", genres='" + genres + '\'' +
                ", companies='" + companies + '\'' +
                ", countries='" + countries + '\'' +
                ", languages='" + languages + '\'' +
                ", posters=" + posters +
                ", metacriticRating='" + metacriticRating + '\'' +
                '}';
    }
}
