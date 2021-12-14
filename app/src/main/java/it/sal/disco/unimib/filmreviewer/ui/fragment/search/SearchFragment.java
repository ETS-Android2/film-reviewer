package it.sal.disco.unimib.filmreviewer.ui.fragment.search;

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
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.adapter.MoviesRecyclerViewAdapterAPI;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.customObj.MoviesResponse;
import it.sal.disco.unimib.filmreviewer.ui.fragment.discover.DiscoverViewModel;
import it.sal.disco.unimib.filmreviewer.utils.Constants;

public class SearchFragment extends Fragment {

    private List<Movie> moviesList;
    private MoviesRecyclerViewAdapterAPI RecyclerViewAdapter;
    private SearchViewModel mSearchViewModel;

    public SearchFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.moviesList = new ArrayList<>();
        mSearchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View this_view = inflater.inflate(R.layout.fragment_search, container, false);



        //RecyclerView
        RecyclerView mRecyclerView = this_view.findViewById(R.id.search_recycler_view);
        //CACHE RECYCLERVIEW /**/
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(35);
        mRecyclerView.setDrawingCacheEnabled(true);
        //FINE CACHE RECYCLERVIEW
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewAdapter =
                new MoviesRecyclerViewAdapterAPI(
                        this.moviesList,
                        movie -> Log.d(
                                "SearchFragment",
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
                    for(int i = 0; i< Constants.API_MAX_FOR_PAGE && i<moviesResponse.getMoviesList().size(); i++){
                        moviesList.add(moviesResponse.getMoviesList().get(i));
                    }
                    RecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        };



        //Button & SearchView
        SearchView searchView = this_view.findViewById(R.id.search_search);
        Button searchButton = this_view.findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String query = searchView.getQuery().toString();
                Log.d("ZZZZZ-", query);
                mSearchViewModel.getMovies(99, query)
                        .observe(getViewLifecycleOwner(), observer);
            }
        });



        return this_view;
    }
}