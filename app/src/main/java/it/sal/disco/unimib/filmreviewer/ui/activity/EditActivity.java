package it.sal.disco.unimib.filmreviewer.ui.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.Executors;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.adapter.ActorsRecyclerViewAdatper;
import it.sal.disco.unimib.filmreviewer.customObj.Actor;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.room.MovieDao;
import it.sal.disco.unimib.filmreviewer.room.MoviesRoomDatabase;

public class EditActivity extends AppCompatActivity {

    private EditActivityViewModel mEditViewModel;
    private Movie currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialization + ViewModel
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mEditViewModel = new ViewModelProvider(this).get(EditActivityViewModel.class);

        //Toolbar
        Toolbar toolbar_mia = findViewById(R.id.edit_toolbar);
        toolbar_mia.setTitle(R.string.editing);
        setSupportActionBar(toolbar_mia);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ProgressBar /**/
        View scv432 = findViewById(R.id.scv432);
        scv432.setVisibility(View.INVISIBLE);
        ProgressBar progressBarEditing = findViewById(R.id.progressBarEditing);
        progressBarEditing.setVisibility(View.VISIBLE);

        //Observer
        @SuppressLint("NotifyDataSetChanged")
        Observer<Movie> observer = movie -> {
            progressBarEditing.setVisibility(View.INVISIBLE);
            scv432.setVisibility(View.VISIBLE);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_edit, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        MoviesRoomDatabase moviesRoomDatabase = MoviesRoomDatabase.getDatabase(getApplicationContext());
        MovieDao mMoviesDao = moviesRoomDatabase.movieDao();

        if(item.getItemId() == R.id.saveButton1){
            Log.d("DEBUG", "SELEZIONATA SAVE");
            //Extra Save

            //1
            EditText textPersonalRev = findViewById(R.id.TextPersonalReview);
            currentMovie.setPrivate_desc(textPersonalRev.getText().toString());

            //2
            RatingBar ratingBarPersonal = findViewById(R.id.ratingBar);
            currentMovie.setPrivate_stars(ratingBarPersonal.getRating());

            //3
            Switch switchPersonal = findViewById(R.id.switch1);
            currentMovie.setPrivate_fav(switchPersonal.isChecked());

            //Execution query
            Executors.newSingleThreadExecutor().execute(() -> {
                mMoviesDao.insertAll(currentMovie);
            });
            finish();
            return true;
        }
        if(item.getItemId() == R.id.deleteButton1){
            Log.d("DEBUG", "SELEZIONATA DELETE");
            Executors.newSingleThreadExecutor().execute(() -> {
                mMoviesDao.deleteSpecificMovie(currentMovie.getId());
            });
            finish();
            return true;
        }
        if(item.getItemId() == android.R.id.home){
            Log.d("DEBUG", "SELEZIONATA BACK");
            finish();
            //onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateInfo(){
        //UI object-related
        TextView titleMovie = findViewById(R.id.movieTitle);
        titleMovie.setText(currentMovie.getTitle());

        TextView releaseMovie = findViewById(R.id.movieRelaseDate);
        releaseMovie.setText(currentMovie.getReleaseDate());

        TextView plotMovie = findViewById(R.id.moviePlot);
        plotMovie.setText(currentMovie.getPlot());

        ImageView imageMovie = findViewById(R.id.movieImage);
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
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, currentMovie.getActorList().size()));
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