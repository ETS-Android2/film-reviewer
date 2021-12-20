package it.sal.disco.unimib.filmreviewer.customObj;

import java.util.List;

public class PosterBox {
    private String imDbId;
    private String title;
    private String fullTitle;
    private String type;
    private String year;
    private List<Poster> posters;


    public PosterBox(String imDbId, String title, String fullTitle, String type, String year, List<Poster> posters) {
        this.imDbId = imDbId;
        this.title = title;
        this.fullTitle = fullTitle;
        this.type = type;
        this.year = year;
        this.posters = posters;
    }

    public String getImDbId() {
        return imDbId;
    }

    public void setImDbId(String imDbId) {
        this.imDbId = imDbId;
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

    public List<Poster> getPosters() {
        return posters;
    }

    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }

    @Override
    public String toString() {
        return "PosterBox{" +
                "imDbId='" + imDbId + '\'' +
                ", title='" + title + '\'' +
                ", fullTitle='" + fullTitle + '\'' +
                ", type='" + type + '\'' +
                ", year='" + year + '\'' +
                ", posters=" + posters +
                '}';
    }
}
