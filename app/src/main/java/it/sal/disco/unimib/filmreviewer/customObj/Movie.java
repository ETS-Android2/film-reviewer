package it.sal.disco.unimib.filmreviewer.customObj;

public class Movie {
    private String id;
    private int rank;
    private String title;
    private String fullTitle;
    private int year;
    private String image;
    private String crew;
    private String imDbRating;
    private String imDbRatingCount;


    private String desc;

    public Movie(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
