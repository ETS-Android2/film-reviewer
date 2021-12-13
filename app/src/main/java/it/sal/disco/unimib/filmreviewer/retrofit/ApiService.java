package it.sal.disco.unimib.filmreviewer.retrofit;

import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {

    //Call rep 1
    @GET("MostPopularMovies")
    Call<MoviesResponse> getMostPopularMovies(
            @Header(Constants.HEADLINES_COUNTRY) String country,
            @Query("apiKey") String apiKey);


    //Call rep 2,
    @GET("Keyword")
    Call<MoviesResponse> getByKeyword(
            @Header(Constants.HEADLINES_COUNTRY) String country,
            @Query("apiKey") String apiKey,
            @Query("id") String query_value);
            /*
            * Use with:
            *   dramas
            *
            */

    //Call rep -
    @GET("Top250Movies")
    Call<MoviesResponse> getTop250(
            @Header(Constants.HEADLINES_COUNTRY) String country,
            @Query("apiKey") String apiKey);


    //Call rep -
    @GET("ComingSoon")
    Call<MoviesResponse> getComingSoon(
            @Header(Constants.HEADLINES_COUNTRY) String country,
            @Query("apiKey") String apiKey);


    //Call rep -
    @GET("InTheaters")
    Call<MoviesResponse> getInTheaters(
            @Header(Constants.HEADLINES_COUNTRY) String country,
            @Query("apiKey") String apiKey);


    //Call rep -
    @GET("MostPopularTVs")
    Call<MoviesResponse> getMostPopularTVs(
            @Header(Constants.HEADLINES_COUNTRY) String country,
            @Query("apiKey") String apiKey);


    //Call rep -
    @GET("Search")
    Call<MoviesResponse> getSearchResult(
            @Header(Constants.HEADLINES_COUNTRY) String country,
            @Query("apiKey") String apiKey,
            @Query("expression") String searchExpression);
}
