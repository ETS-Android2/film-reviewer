package it.sal.disco.unimib.filmreviewer.ui.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

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

    /*
    public MutableLiveData<NewsResponse> getNews(String country) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - Constants.LAST_TIME_UPDATE > Constants.TIME_TO_WAIT){
            Constants.LAST_TIME_UPDATE = currentTime;
            mNewsResponseLiveData = mINewsRepository.getNewsOnlineInDB(country);

        }else{
            mNewsResponseLiveData = mINewsRepository.fetchNews(country);
        }
        return mNewsResponseLiveData;
    }*/

    public MutableLiveData<MoviesResponse> getNews() {
        mMoviesResponseLiveData = mIMoviesRepository.getNewsTest();
        return mMoviesResponseLiveData;
    }
}