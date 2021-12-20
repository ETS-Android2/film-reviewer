package it.sal.disco.unimib.filmreviewer.ui.activity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.repository.MoviesRepository;
import it.sal.disco.unimib.filmreviewer.repository.iMoviesRepository;
import it.sal.disco.unimib.filmreviewer.utils.Constants;

public class EditActivityViewModel extends AndroidViewModel {

    private final iMoviesRepository mIMoviesRepository;
    private MutableLiveData<Movie> mMoviesResponseLiveData;


    public EditActivityViewModel(@NonNull Application application) {
        super(application);
        mIMoviesRepository = new MoviesRepository(application);
    }

    public MutableLiveData<Movie> getSpecificMovies() {
        if(Constants.selectedMovie.getId() != null){
            mMoviesResponseLiveData = mIMoviesRepository.getSelectedMovie(Constants.selectedMovie.getId());
        }
        return mMoviesResponseLiveData;
    }


}
