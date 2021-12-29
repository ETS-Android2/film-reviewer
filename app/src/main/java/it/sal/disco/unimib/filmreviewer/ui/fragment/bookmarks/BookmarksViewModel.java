package it.sal.disco.unimib.filmreviewer.ui.fragment.bookmarks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.repository.MoviesRepository;
import it.sal.disco.unimib.filmreviewer.repository.iMoviesRepository;

public class BookmarksViewModel extends AndroidViewModel {

    private final iMoviesRepository mIMoviesRepository;
    private MutableLiveData<MoviesResponse> mMoviesResponseLiveData;

    public BookmarksViewModel(@NonNull Application application) {
        super(application);
        mIMoviesRepository = new MoviesRepository(application);
    }

    public MutableLiveData<MoviesResponse> getLocalMoviesFav() {
        mMoviesResponseLiveData = mIMoviesRepository.getLocalMoviesFav();
        return mMoviesResponseLiveData;
    }
}
