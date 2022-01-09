package it.sal.disco.unimib.filmreviewer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
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

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executors;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.adapter.ActorsRecyclerViewAdatper;
import it.sal.disco.unimib.filmreviewer.customObj.Actor;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.room.MovieDao;
import it.sal.disco.unimib.filmreviewer.room.MoviesRoomDatabase;
import it.sal.disco.unimib.filmreviewer.utils.Constants;

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
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        //ProgressBar
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.saveButton1){
            Log.d("DEBUG", "Selected SAVE");

            EditText textPersonalRev = findViewById(R.id.TextPersonalReview);
            currentMovie.setPrivate_desc(textPersonalRev.getText().toString());

            RatingBar ratingBarPersonal = findViewById(R.id.ratingBar);
            currentMovie.setPrivate_stars(ratingBarPersonal.getRating());

            Switch switchPersonal = findViewById(R.id.switch1);
            currentMovie.setPrivate_fav(switchPersonal.isChecked());

            mEditViewModel.insertDBSpecificMovie(currentMovie);
            onBackPressed();
            return true;
        }
        if(item.getItemId() == R.id.deleteButton1){
            Log.d("DEBUG", "Selected DELETE");
            mEditViewModel.deleteDBSpecificMovie(currentMovie.getId());
            onBackPressed();
            return true;
        }
        if(item.getItemId() == android.R.id.home){
            Log.d("DEBUG", "Selected BACK");
            //finish();
            onBackPressed();
            return true;
        }
        if(item.getItemId() == R.id.shareButton1){
            Log.d("DEBUG", "Selected SHARE");

            //Preparing Text to share
            String text_to_share = "";
            text_to_share += getString(R.string.title1)+" "+currentMovie.getTitle()+"\n";
            text_to_share += getString(R.string.Prating)+" "+ currentMovie.getPrivate_stars() +"/5.0"+"\n";
            String fav;
            if(currentMovie.isPrivate_fav()){
                fav = getString(R.string.yes);
            }else{
                fav = getString(R.string.no);
            }
            text_to_share += getString(R.string.Pfav)+" "+ fav +"\n";
            text_to_share += getString(R.string.Pdesc)+" "+ currentMovie.getPrivate_desc() +"\n";

            //launch intent share
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text_to_share);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);

            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateInfo(){
        TextView titleMovie = findViewById(R.id.movieTitle);
        titleMovie.setText(currentMovie.getTitle());

        TextView releaseMovie = findViewById(R.id.movieRelaseDate);
        releaseMovie.setText(currentMovie.getReleaseDate());

        TextView plotMovie = findViewById(R.id.moviePlot);
        if(currentMovie.getPlotLocal().isEmpty()){
            plotMovie.setText(currentMovie.getPlot());
        }else{
            plotMovie.setText(currentMovie.getPlotLocal());
        }

        ImageView imageMovie = findViewById(R.id.movieImage);
        String link1poster;
        if(currentMovie.getPosters().getPosters().size() > 0){
            link1poster = currentMovie.getPosters().getPosters().get(0).getLink();
        }else{
            link1poster = "";
        }
        Constants.showImage(6, imageMovie, link1poster, imageMovie);

        RatingBar ratingbarMovie = findViewById(R.id.ratingBar);
        ratingbarMovie.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d("DEBUG", "Stars Changed to: "+String.valueOf(rating));
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

        EditText textPersonalRev = findViewById(R.id.TextPersonalReview);
        textPersonalRev.setText(currentMovie.getPrivate_desc());

        RatingBar ratingBarPersonal = findViewById(R.id.ratingBar);
        ratingBarPersonal.setRating(currentMovie.getPrivate_stars());

        Switch switchPersonal = findViewById(R.id.switch1);
        switchPersonal.setChecked(currentMovie.isPrivate_fav());
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
        finish();
    }
}