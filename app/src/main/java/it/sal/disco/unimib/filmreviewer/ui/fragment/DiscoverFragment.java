package it.sal.disco.unimib.filmreviewer.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.adapter.MoviesRecyclerViewAdapterAPI;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;


public class DiscoverFragment extends Fragment {

    private List<Movie> moviesList;
    private MoviesRecyclerViewAdapterAPI RecyclerViewAdapter;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.moviesList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View this_view = inflater.inflate(R.layout.fragment_discover, container, false);


        //Something to print
        for(int i=0; i<20; i++){
            moviesList.add(new Movie("Title"+i, "Desc"+i));
        }


        //RecyclerView
        RecyclerView countryNewsRecyclerView = this_view.findViewById(R.id.discover_recycler_view);
        countryNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewAdapter =
                new MoviesRecyclerViewAdapterAPI(
                        this.moviesList,
                        movie -> Log.d("DiscoverFragment", "Click RecycleView element " + movie.getTitle()));
        countryNewsRecyclerView.setAdapter(RecyclerViewAdapter);

        return this_view;
    }
}