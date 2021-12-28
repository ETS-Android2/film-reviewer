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
import it.sal.disco.unimib.filmreviewer.room.MovieDao;
import it.sal.disco.unimib.filmreviewer.room.MoviesRoomDatabase;
import it.sal.disco.unimib.filmreviewer.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository implements iMoviesRepository{
    private Application mApplication;
    private MovieDao mMoviesDao;
    private MutableLiveData<MoviesResponse> mLiveData;
    private MutableLiveData<Movie> mLiveDataMovie;

    public MoviesRepository(Application mApplication){
        this.mApplication = mApplication;
        this.mLiveData = new MutableLiveData<>();
        MoviesRoomDatabase moviesRoomDatabase =
                MoviesRoomDatabase.getDatabase(mApplication.getApplicationContext());
        this.mMoviesDao = moviesRoomDatabase.movieDao();
    }

    //Functions for Discover&Search Fragment
    @Override
    public MutableLiveData<MoviesResponse> getMovies(int selector, String opz_param) {
        mLiveData = new MutableLiveData<>();
        Call<MoviesResponse> moviesResponseCall = getCorrectApiService(selector, opz_param);
        moviesResponseCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                Executors.newSingleThreadExecutor().execute(() -> {
                    Log.d("DEBUG", "onResponse-getMovies");
                    if(response.body() != null){
                        List<Movie> movieList = response.body().getMoviesList();
                        mLiveData.postValue(new MoviesResponse(movieList));
                    }else{
                        Log.d("DEBUG", "onResponse-getMovies-errorResponse");
                    }
                });
            }
            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                Log.d("DEBUG", "onFailure-getNewsOnlineInDB-"+t.toString());
            }});
        return mLiveData;
    }

    public static Call<MoviesResponse> getCorrectApiService(int input, String opz_param){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService mApiService = retrofit.create(ApiService.class);
        Call<MoviesResponse> MoviesResponseCall = null;

        if(input == 0){
            MoviesResponseCall = mApiService
                    .getMostPopularMovies(Constants.HEADLINES_COUNTRY, Constants.API_KEY);
        }

        if(input == 1){
            MoviesResponseCall = mApiService
                    .getTop250(Constants.HEADLINES_COUNTRY, Constants.API_KEY);
        }

        if(input == 2){
            MoviesResponseCall = mApiService
                    .getComingSoon(Constants.HEADLINES_COUNTRY, Constants.API_KEY);
        }

        if(input == 3){
            MoviesResponseCall = mApiService
                    .getInTheaters(Constants.HEADLINES_COUNTRY, Constants.API_KEY);
        }

        if(input == 4){
            MoviesResponseCall = mApiService
                    .getMostPopularTVs(Constants.HEADLINES_COUNTRY, Constants.API_KEY);
        }

        if(input == 1000){
            MoviesResponseCall = mApiService
                    .getByKeyword(
                            Constants.HEADLINES_COUNTRY,
                            Constants.API_KEY,
                            "dramas");
        }

        if(input == 99){
            MoviesResponseCall = mApiService
                    .getSearchResult(
                            Constants.HEADLINES_COUNTRY,
                            Constants.API_KEY,
                            opz_param);
        }

        return MoviesResponseCall;
    }

    //Functions for EditActivity
    public void insertDBSpecificMovie(Movie movie_input){
        Executors.newSingleThreadExecutor().execute(() -> {
            mMoviesDao.insertAll(movie_input);
        });
    }

    public void deleteDBSpecificMovie(String movie_id){
        Executors.newSingleThreadExecutor().execute(() -> {
            mMoviesDao.deleteSpecificMovie(movie_id);
        });
    }

    @Override
    public MutableLiveData<Movie> getMovieLocalOROnline(String id) {
        mLiveDataMovie = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            Movie movie_query = mMoviesDao.getMovieFromID(id);
            Log.d("DEBUG", "CALL LOCAL DB");

            if(movie_query == null){
                Log.d("DEBUG", "movie_query is == null");

                //Retrofit initialization
                Retrofit retrofit = new Retrofit
                        .Builder()
                        .baseUrl(Constants.API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiService mApiService = retrofit.create(ApiService.class);

                //Callback
                Call<Movie> movieResponseCall = mApiService
                        .getSpecificMovie(
                                Constants.HEADLINES_COUNTRY,
                                Constants.API_KEY,
                                id,
                                "Posters");
                movieResponseCall.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                        Executors.newSingleThreadExecutor().execute(() -> {
                            Log.d("DEBUG", "onResponse-getSelectedMovie");
                            if(response.body() != null){
                                Movie selected = response.body();
                                mLiveDataMovie.postValue(selected);
                            }else{
                                Log.d("DEBUG", "onResponse-getSelectedMovie-errorResponse");
                            }
                        });
                    }
                    @Override
                    public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                        Log.d("DEBUG", "onFailure-getSelectedMovie"+t.toString());
                    }});
            }else{
                Log.d("DEBUG", "movie_query is != null");
                mLiveDataMovie.postValue(movie_query);
            }
        });
        return mLiveDataMovie;
    }

    //Functions for Collection&Favourite Fragment
    @Override
    public MutableLiveData<MoviesResponse> getLocalMovies() {
        mLiveData = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            Log.d("DEBUG", "getLocalMovies");
            MoviesResponse movie_response= new MoviesResponse(mMoviesDao.getAll());
            mLiveData.postValue(movie_response);
        });
        return mLiveData;
    }
}
