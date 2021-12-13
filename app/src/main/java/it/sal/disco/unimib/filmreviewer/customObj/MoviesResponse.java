package it.sal.disco.unimib.filmreviewer.customObj;

import java.util.List;

public class MoviesResponse {
    private String keyword;
    private List<Movie> items;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public MoviesResponse(List<Movie> moviesList) {
        this.items = moviesList;
    }

    public MoviesResponse(List<Movie> moviesList, String errorMessage) {
        this.items = moviesList;
        this.errorMessage = errorMessage;
    }

    public MoviesResponse(String keyword, List<Movie> items, String errorMessage) {
        this.keyword = keyword;
        this.items = items;
        this.errorMessage = errorMessage;
    }

    public List<Movie> getMoviesList() {
        return items;
    }
}
