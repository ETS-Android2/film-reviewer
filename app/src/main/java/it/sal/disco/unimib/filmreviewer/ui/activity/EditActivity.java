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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialization + ViewModel
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mEditViewModel = new ViewModelProvider(this).get(EditActivityViewModel.class);

        //UI object-related
        TextView titleMovie = findViewById(R.id.movieTitle);
        TextView fulltitleMovie = findViewById(R.id.movieFullTitle);
        TextView relaseMovie = findViewById(R.id.movieRelaseDate);
        TextView plotMovie = findViewById(R.id.moviePlot);
        ImageView imageMovie = findViewById(R.id.movieImage);


        //TESTING
        //currentMovie = null;  //da mettere piu avanti

        /*
            //ProgressBar
        ProgressBar pgrbar = this_view.findViewById(R.id.discover_progressBar);
        pgrbar.setVisibility(View.INVISIBLE);
        */

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

                //aggiornaInformazioniUI();
                titleMovie.setText(currentMovie.getTitle());
                fulltitleMovie.setText(currentMovie.getFullTitle());
                relaseMovie.setText(currentMovie.getReleaseDate());
                plotMovie.setText(currentMovie.getPlot());
                Picasso.get().load(currentMovie.getImage()).into(imageMovie);


            }
        };
        mEditViewModel.getSpecificMovies()
                .observe(this, observer);
    }
}