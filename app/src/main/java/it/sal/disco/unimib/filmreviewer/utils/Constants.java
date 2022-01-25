package it.sal.disco.unimib.filmreviewer.utils;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Locale;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;

public class Constants {

    //API
    //public final static String API_KEY = "k_lkvlsrmu";
    public final static String API_KEY = "k_613gh8jx";
    //public final static String API_KEY = "k_ay29tyqr";

    public final static String HEADLINES_COUNTRY = "en";
    public final static int API_MAX_FOR_PAGE = 35;

    public static String get_apiBaseurl(){
        String country = Locale.getDefault().getLanguage();
        return "https://imdb-api.com/"+country+"/API/";
    }

    //DB
    public final static String NEWS_DATABASE_NAME = "movies_db";

    //Utility global functions (For Images)
    public static String getLittleImage(String inputUrl){
        return inputUrl;
    }

    public static int[] imagePosterSize(int lambda){
        int[] aa = {0, 0};
        aa[0] = lambda*27;
        aa[1] = lambda*40;
        return aa;
    }

    public static RequestOptions getRightOptions(int lambda){
        int[] aa = Constants.imagePosterSize(lambda);
        RequestOptions myOptions = new RequestOptions()
                .override(aa[0], aa[1])
                .centerCrop();
        return myOptions;
    }

    public static void showImage(int lambda, View itemView, String url_image, ImageView destination){
        Glide.with(itemView)
                .asBitmap()
                .apply(Constants.getRightOptions(lambda))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load(url_image)
                .into(destination);
    }

    //Use ONLY to pass the id, the consistency is not guaranteed for this object
    public static Movie selectedMovie = null;

    //CACHE RECYCLERVIEW
    /*
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setItemViewCacheSize(35);
    mRecyclerView.setDrawingCacheEnabled(true);
    */
}
