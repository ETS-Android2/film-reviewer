package it.sal.disco.unimib.filmreviewer.repository;

import androidx.lifecycle.MutableLiveData;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;

public interface iMoviesRepository {
    MutableLiveData<MoviesResponse> getMovies(int selector, String opz_param);
    MutableLiveData<MoviesResponse> getLocalMovies();
    MutableLiveData<MoviesResponse> getLocalMoviesFav();
    void insertDBSpecificMovie(Movie movie_input);
    void deleteDBSpecificMovie(String movie_id);
    MutableLiveData<Movie> getMovieLocalOROnline(String id);
}
