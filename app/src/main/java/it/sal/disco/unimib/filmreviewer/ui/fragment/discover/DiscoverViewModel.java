package it.sal.disco.unimib.filmreviewer.ui.fragment.discover;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.repository.MoviesRepository;
import it.sal.disco.unimib.filmreviewer.repository.iMoviesRepository;


public class DiscoverViewModel extends AndroidViewModel {

    private final iMoviesRepository mIMoviesRepository;
    private MutableLiveData<MoviesResponse> mMoviesResponseLiveData;

    public DiscoverViewModel(@NonNull Application application) {
        super(application);
        mIMoviesRepository = new MoviesRepository(application);
    }

    public MutableLiveData<MoviesResponse> getMovies(int selector, String opz_param) {
        mMoviesResponseLiveData = mIMoviesRepository.getMovies(selector, opz_param);
        return mMoviesResponseLiveData;
    }

}