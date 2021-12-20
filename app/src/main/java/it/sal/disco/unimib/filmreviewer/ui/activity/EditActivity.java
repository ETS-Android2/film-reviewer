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
        TextView fulltitleMovie = findViewById(R.id.movieFullTitle);
        TextView relaseMovie = findViewById(R.id.movieRelaseDate);
        TextView plotMovie = findViewById(R.id.moviePlot);
        ImageView imageMovie = findViewById(R.id.movieImage);


        //Check if there are posters
        String link1poster;
        if(currentMovie.getPosters().getPosters().size() > 0){
            link1poster = currentMovie.getPosters().getPosters().get(0).getLink();
        }else{
            link1poster = "";
        }


        //UI effective update
        titleMovie.setText(currentMovie.getTitle());
        fulltitleMovie.setText(currentMovie.getFullTitle());
        relaseMovie.setText("Release: "+currentMovie.getReleaseDate());
        plotMovie.setText(currentMovie.getPlot());
        Picasso.get().load(link1poster).placeholder(R.drawable.ic_baseline_movie_filter_24).into(imageMovie);
    }
}