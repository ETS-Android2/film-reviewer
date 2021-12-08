package it.sal.disco.unimib.filmreviewer.customObj;

import java.util.List;

public class MoviesResponse {
    public List<Movie> moviesList;

    public MoviesResponse(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }
}
