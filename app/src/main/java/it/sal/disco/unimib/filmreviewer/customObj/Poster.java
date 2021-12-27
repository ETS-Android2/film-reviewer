package it.sal.disco.unimib.filmreviewer.customObj;

public class Poster {
    private String id;
    private String link;
    private String aspectRatio;
    private String language;
    private String width;
    private String height;


    public Poster(String id, String link, String aspectRatio, String language, String width, String height) {
        this.id = id;
        this.link = link;
        this.aspectRatio = aspectRatio;
        this.language = language;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", aspectRatio='" + aspectRatio + '\'' +
                ", language='" + language + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}

