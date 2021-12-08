package it.sal.disco.unimib.filmreviewer.retrofit;

import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    @GET(Constants.HEADLINES)
    Call<MoviesResponse> getNews(
            @Header(Constants.HEADLINES_COUNTRY_PR) String country,
            @Query(Constants.HEADLINES_APIKEY_PR) String apiKey);
}
