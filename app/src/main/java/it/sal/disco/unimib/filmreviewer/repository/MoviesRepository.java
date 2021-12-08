package it.sal.disco.unimib.filmreviewer.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;

public class MoviesRepository implements iMoviesRepository{
    private Application mApplication;

    public MoviesRepository(Application mApplication){
        this.mApplication = mApplication;
    }


    @Override
    public MutableLiveData<MoviesResponse> getNewsTest() {

        List<Movie> moviesList = new ArrayList<>();
        for(int i=0; i<20; i++){
            moviesList.add(new Movie("Title"+i, "Desc"+i));
        }

        MutableLiveData<MoviesResponse> mLiveData = new MutableLiveData<>();
        mLiveData.postValue(new MoviesResponse(moviesList));
        return mLiveData;
    }
}
