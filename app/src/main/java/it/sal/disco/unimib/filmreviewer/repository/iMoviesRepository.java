package it.sal.disco.unimib.filmreviewer.repository;

import androidx.lifecycle.MutableLiveData;

import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;

public interface iMoviesRepository {
    MutableLiveData<MoviesResponse> getNewsTest();
    MutableLiveData<MoviesResponse> getNewsMostPopular();
}
