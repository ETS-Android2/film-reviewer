package it.sal.disco.unimib.filmreviewer.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.adapter.ActorsRecyclerViewAdatper;
import it.sal.disco.unimib.filmreviewer.customObj.Actor;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_edit, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.saveButton1){
            Log.d("DEBUG", "SELEZIONATA SAVE");
            return true;
        }
        if(item.getItemId() == R.id.deleteButton1){
            Log.d("DEBUG", "SELEZIONATA DELETE");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateInfo(){
        //UI object-related
        TextView titleMovie = findViewById(R.id.movieTitle);
        titleMovie.setText(currentMovie.getTitle());

        /*
        TextView fullTitleMovie = findViewById(R.id.movieFullTitle);
        fullTitleMovie.setText(currentMovie.getFullTitle());*/

        TextView releaseMovie = findViewById(R.id.movieRelaseDate);
        releaseMovie.setText(currentMovie.getReleaseDate());

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


        //Toolbar
        Toolbar toolbar_mia = findViewById(R.id.edit_toolbar);
        toolbar_mia.setTitle(currentMovie.getTitle());
        setSupportActionBar(toolbar_mia);
    }
}