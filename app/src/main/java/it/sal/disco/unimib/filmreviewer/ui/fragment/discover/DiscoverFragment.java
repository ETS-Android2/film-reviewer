package it.sal.disco.unimib.filmreviewer.ui.fragment.discover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.adapter.MoviesRecyclerViewAdapterAPI;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.ui.activity.EditActivity;
import it.sal.disco.unimib.filmreviewer.utils.Constants;


public class DiscoverFragment extends Fragment {

    private List<Movie> moviesList;
    private MoviesRecyclerViewAdapterAPI RecyclerViewAdapter;
    private DiscoverViewModel mDiscoverViewModel;
    private int selected_index;

    public DiscoverFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.moviesList = new ArrayList<>();
        mDiscoverViewModel = new ViewModelProvider(requireActivity()).get(DiscoverViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View this_view = inflater.inflate(R.layout.fragment_discover, container, false);

        //Spinner
        Spinner mSpinnerDiscovery = this_view.findViewById(R.id.spinner_discover);
        mSpinnerDiscovery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id){
                selected_index = mSpinnerDiscovery.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Clear
        moviesList.clear();

        //ProgressBar
        ProgressBar pgrbar = this_view.findViewById(R.id.discover_progressBar);
        pgrbar.setVisibility(View.INVISIBLE);
        pgrbar.bringToFront();

        //RecyclerView
        RecyclerView mRecyclerView = this_view.findViewById(R.id.discover_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewAdapter =
                new MoviesRecyclerViewAdapterAPI(this.moviesList, new MoviesRecyclerViewAdapterAPI.OnItemClickListener() {
                    @Override
                    public void onItemClick(Movie movie) {
                        //DEBUG print
                        Log.d(
                                "DiscoverFragment",
                                "CLICKED RecycleView element " + movie.toString());

                        //Set current movie as movie to be edited
                        Constants.selectedMovie = movie;

                        //launch intent EditActivity
                        Intent intentI = new Intent(getContext(), EditActivity.class);
                        startActivity(intentI);
                    }
                });
        mRecyclerView.setAdapter(RecyclerViewAdapter);

        //Observer for when View model change state
        @SuppressLint("NotifyDataSetChanged")
        Observer<MoviesResponse> observer = moviesResponse -> {
            pgrbar.setVisibility(View.INVISIBLE);
            if (moviesResponse.getMoviesList() == null) {
                Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        "Can't load movies - List null",
                        Snackbar.LENGTH_LONG)
                        .show();
            }else{
                if (moviesResponse.getMoviesList() != null) {
                    moviesList.clear();
                    for(int i=0; i<Constants.API_MAX_FOR_PAGE && i<moviesResponse.getMoviesList().size(); i++){
                        moviesList.add(moviesResponse.getMoviesList().get(i));
                    }
                    RecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        };

        //Button
        Button discoveryButton = this_view.findViewById(R.id.button_discover);
        discoveryButton.setOnClickListener(v -> {
            pgrbar.setVisibility(View.VISIBLE);
            mDiscoverViewModel.getMovies(selected_index, null)
                    .observe(getViewLifecycleOwner(), observer);
        });

        return this_view;
    }
}