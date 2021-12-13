package it.sal.disco.unimib.filmreviewer.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.retrofit.ApiService;
import it.sal.disco.unimib.filmreviewer.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository implements iMoviesRepository{
    private Application mApplication;
    //private final NewsDao mNewsDao;
    private MutableLiveData<MoviesResponse> mLiveData;
    private final MoviesRepository reference_to_repository = this;

    public MoviesRepository(Application mApplication){
        this.mApplication = mApplication;
        this.mLiveData = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<MoviesResponse> getNewsMostPopular(int selector) {
        Call<MoviesResponse> newsResponseCall = getCorrectApiService(selector);
        newsResponseCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                Executors.newSingleThreadExecutor().execute(() -> {
                    Log.d("AAAAA", "MoviesRepository - onResponse - getNewsOnlineInDB");
                    Log.d("AAAAA", response.toString());
                    if(response.body() != null){
                        List<Movie> newsList = response.body().getMoviesList();
                        mLiveData.postValue(new MoviesResponse(newsList));
                    }else{
                        Log.d("AAAAA", "MoviesRepository - onResponse - getNewsOnlineInDB - ERROR response");
                    }
                });
            }
            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                Log.d("AAAAA", "MoviesRepository - onFailure - getNewsOnlineInDB");
                Log.d("AAAAA", t.toString());
            }});
        return mLiveData;
    }

    public static Call<MoviesResponse> getCorrectApiService(int input){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService mApiService = retrofit.create(ApiService.class);
        Call<MoviesResponse> newsResponseCall = null;

        if(input == 0){
            newsResponseCall = mApiService
                    .getMostPopularMovies(Constants.HEADLINES_COUNTRY, Constants.API_KEY);
        }

        if(input == 1){
            newsResponseCall = mApiService
                    .getTop250(Constants.HEADLINES_COUNTRY, Constants.API_KEY);
        }

        if(input == 2){
            newsResponseCall = mApiService
                    .getComingSoon(Constants.HEADLINES_COUNTRY, Constants.API_KEY);
        }

        if(input == 3){
            newsResponseCall = mApiService
                    .getInTheaters(Constants.HEADLINES_COUNTRY, Constants.API_KEY);
        }

        if(input == 4){
            newsResponseCall = mApiService
                    .getMostPopularTVs(Constants.HEADLINES_COUNTRY, Constants.API_KEY);
        }

        if(input == 1000){
            newsResponseCall = mApiService
                    .getByKeyword(
                            Constants.HEADLINES_COUNTRY,
                            Constants.API_KEY,
                            "dramas");
        }

        return newsResponseCall;
    }

}
