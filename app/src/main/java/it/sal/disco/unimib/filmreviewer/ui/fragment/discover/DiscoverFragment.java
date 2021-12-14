package it.sal.disco.unimib.filmreviewer.ui.fragment.discover;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.adapter.MoviesRecyclerViewAdapterAPI;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.utils.Constants;


public class DiscoverFragment extends Fragment {

    private List<Movie> moviesList;
    private MoviesRecyclerViewAdapterAPI RecyclerViewAdapter;
    private DiscoverViewModel mDiscoverViewModel;
    private int selected_index;

    public DiscoverFragment() {
        // Required empty public constructor
    }

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

        moviesList.clear();

        //RecyclerView
        RecyclerView mRecyclerView = this_view.findViewById(R.id.discover_recycler_view);
        //CACHE RECYCLERVIEW
        /*
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(35);
        mRecyclerView.setDrawingCacheEnabled(true);
        */
        //FINE CACHE RECYCLERVIEW
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewAdapter =
                new MoviesRecyclerViewAdapterAPI(
                        this.moviesList,
                        movie -> Log.d(
                                "DiscoverFragment",
                                "CLICKED RecycleView element " + movie.toString()));
        mRecyclerView.setAdapter(RecyclerViewAdapter);

        //Observer for when View model change state
        @SuppressLint("NotifyDataSetChanged")
        Observer<MoviesResponse> observer = moviesResponse -> {
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
                    //moviesList.addAll(moviesResponse.getMoviesList());
                    RecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        };

        //Button
        Button discoveryButton = this_view.findViewById(R.id.button_discover);
        discoveryButton.setOnClickListener(v -> {
            mDiscoverViewModel.getMovies(selected_index, null)
                    .observe(getViewLifecycleOwner(), observer);
        });

        return this_view;
    }
}