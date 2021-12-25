package it.sal.disco.unimib.filmreviewer.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.adapter.ActorsRecyclerViewAdatper;
import it.sal.disco.unimib.filmreviewer.adapter.MoviesRecyclerViewAdapterAPI;
import it.sal.disco.unimib.filmreviewer.customObj.Actor;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;
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
        relaseMovie.setText(currentMovie.getReleaseDate());

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


        TextView typeMovie = findViewById(R.id.movieType);
        typeMovie.setText(currentMovie.getType());

        TextView runtimeMovie = findViewById(R.id.movieRuntime);
        runtimeMovie.setText(currentMovie.getRuntimeStr());

        TextView awardsMovie = findViewById(R.id.movieAwards);
        awardsMovie.setText(currentMovie.getAwards());

        TextView origTitleMovie = findViewById(R.id.movieOriginalTitle);
        origTitleMovie.setText(currentMovie.getOriginalTitle());

        TextView directorMovie = findViewById(R.id.movieDirectors);
        directorMovie.setText(currentMovie.getDirectors());

        TextView genreMovie = findViewById(R.id.movieGenres);
        genreMovie.setText(currentMovie.getGenres());

        TextView companiesMovie = findViewById(R.id.movieCompanies);
        companiesMovie.setText(currentMovie.getCompanies());

        TextView countriesMovie = findViewById(R.id.movieCountries);
        countriesMovie.setText(currentMovie.getCountries());

        TextView languagesMovie = findViewById(R.id.movieLanguages);
        languagesMovie.setText(currentMovie.getLanguages());

        ProgressBar imdb_bar = findViewById(R.id.progressBarIMDB);
        imdb_bar.setMax(10);
        try{

            String aaa = currentMovie.getImDbRating();
            double bbb = Double.parseDouble(aaa);
            int ccc = (int) bbb;

            imdb_bar.setProgress(ccc);
        }catch(Exception e){}

        ProgressBar metacritic = findViewById(R.id.progressBarMETACRITIC);
        metacritic.setMax(100);
        try{

            String aaa = currentMovie.getMetacriticRating();
            double bbb = Double.parseDouble(aaa);
            int ccc = (int) bbb;

            metacritic.setProgress(ccc);
        }catch(Exception e){}


        //RecyclerViewActors
        RecyclerView mRecyclerView = findViewById(R.id.recyclerViewActors);
        ActorsRecyclerViewAdatper RecyclerViewAdapter;
        //CACHE RECYCLERVIEW
        /*
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(35);
        mRecyclerView.setDrawingCacheEnabled(true);
        */
        //FINE CACHE RECYCLERVIEW
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter = new ActorsRecyclerViewAdatper(currentMovie.getActorList(), new ActorsRecyclerViewAdatper.OnItemClickListener() {
            @Override
            public void onItemClick(Actor actor) {
                Log.d(
                        "EditActivity",
                        "CLICKED RecycleView element " + actor.toString());
            }
        });
        mRecyclerView.setAdapter(RecyclerViewAdapter);
    }
}