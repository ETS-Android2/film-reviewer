package it.sal.disco.unimib.filmreviewer.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.ui.fragment.discover.DiscoverViewModel;
import it.sal.disco.unimib.filmreviewer.utils.Constants;

public class EditActivity extends AppCompatActivity {

    private EditActivityViewModel mEditViewModel;
    private Movie currentMovie; //sostituto di moviesList
    private String oldImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialization + ViewModel
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mEditViewModel = new ViewModelProvider(this).get(EditActivityViewModel.class);


        /*
        //ProgressBar
        ProgressBar pgrbar = this_view.findViewById(R.id.discover_progressBar);
        pgrbar.setVisibility(View.INVISIBLE);
        */


        //Observer
        @SuppressLint("NotifyDataSetChanged")
        Observer<Movie> observer = movie -> {
            //pgrbar.setVisibility(View.INVISIBLE);
            if (movie == null) {
                Snackbar.make(
                        this.findViewById(android.R.id.content),
                        "Can't load specific movie - is null",
                        Snackbar.LENGTH_LONG)
                        .show();
            }else{
                currentMovie = movie;
                updateInfo();
            }
        };
        mEditViewModel.getSpecificMovies()
                .observe(this, observer);
    }


    private void updateInfo(){
        //UI object-related
        TextView titleMovie = findViewById(R.id.movieTitle);
        titleMovie.setText(currentMovie.getTitle());

        TextView fulltitleMovie = findViewById(R.id.movieFullTitle);
        fulltitleMovie.setText(currentMovie.getFullTitle());

        TextView relaseMovie = findViewById(R.id.movieRelaseDate);
        relaseMovie.setText("Release: "+currentMovie.getReleaseDate());

        TextView plotMovie = findViewById(R.id.moviePlot);
        plotMovie.setText(currentMovie.getPlot());

        ImageView imageMovie = findViewById(R.id.movieImage);
        //Check if there are posters
        String link1poster;
        if(currentMovie.getPosters().getPosters().size() > 0){
            link1poster = currentMovie.getPosters().getPosters().get(0).getLink();
        }else{
            link1poster = "";
        }
        Picasso.get().load(link1poster).placeholder(R.drawable.ic_baseline_movie_filter_24).into(imageMovie);

        RatingBar ratingbarMovie = findViewById(R.id.ratingBar);
        ratingbarMovie.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d("XXXX", String.valueOf(rating));
            }
        });

        TextView lengMovie = findViewById(R.id.movieLanguages);
        lengMovie.setText("Languages: "+currentMovie.getLanguages());

    }
}