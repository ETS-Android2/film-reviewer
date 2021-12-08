package it.sal.disco.unimib.filmreviewer.ui.fragment;

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

        //RecyclerView
        RecyclerView countryNewsRecyclerView = this_view.findViewById(R.id.discover_recycler_view);
        countryNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewAdapter =
                new MoviesRecyclerViewAdapterAPI(
                        this.moviesList,
                        movie -> Log.d(
                                "DiscoverFragment",
                                "CLICKED RecycleView element " + movie.toString()));
        countryNewsRecyclerView.setAdapter(RecyclerViewAdapter);

        //Observer for when View model change state
        @SuppressLint("NotifyDataSetChanged")
        Observer<MoviesResponse> observer = moviesResponse -> {
            if (moviesResponse.getMoviesList() == null) {
                Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        "Error moviesResponse",
                        Snackbar.LENGTH_LONG)
                        .show();
            }else{
                if (moviesResponse.getMoviesList() != null) {
                    moviesList.clear();
                    for(int i=0; i<Constants.API_MAX_FOR_PAGE; i++){
                        moviesList.add(moviesResponse.getMoviesList().get(i));
                    }
                    //moviesList.addAll(moviesResponse.getMoviesList());
                    RecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        };
        mDiscoverViewModel.getNews().observe(getViewLifecycleOwner(), observer);
        return this_view;
    }
}