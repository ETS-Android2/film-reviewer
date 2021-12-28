package it.sal.disco.unimib.filmreviewer.ui.fragment.collection;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.repository.MoviesRepository;
import it.sal.disco.unimib.filmreviewer.repository.iMoviesRepository;

public class CollectionViewModel extends AndroidViewModel {

    private final iMoviesRepository mIMoviesRepository;
    private MutableLiveData<MoviesResponse> mMoviesResponseLiveData;

    public CollectionViewModel(@NonNull Application application) {
        super(application);
        mIMoviesRepository = new MoviesRepository(application);
    }

    public MutableLiveData<MoviesResponse> getLocalMovies() {
        mMoviesResponseLiveData = mIMoviesRepository.getLocalMovies();
        return mMoviesResponseLiveData;
    }
}
