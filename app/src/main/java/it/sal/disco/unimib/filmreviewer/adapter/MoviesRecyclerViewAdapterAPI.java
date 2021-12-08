package it.sal.disco.unimib.filmreviewer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;

public class MoviesRecyclerViewAdapterAPI extends RecyclerView.Adapter<MoviesRecyclerViewAdapterAPI.MoviesRecyclerViewHolderAPI> {

    public interface OnItemClickListener{
        void onItemClick(Movie movie);
    }
    private OnItemClickListener onItemClickListener;

    private List<Movie> mMoviesList;

    public MoviesRecyclerViewAdapterAPI(List<Movie> mMoviesList, OnItemClickListener OnItemClickListener){
        this.mMoviesList = mMoviesList;
        this.onItemClickListener = OnItemClickListener;
    }

    @NonNull
    @Override
    public MoviesRecyclerViewHolderAPI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_layout_api,
                parent,
                false);

        return new MoviesRecyclerViewHolderAPI(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesRecyclerViewHolderAPI holder, int position) {
        holder.bind(mMoviesList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mMoviesList != null){
            return mMoviesList.size();
        }
        return 0;
    }

    public class MoviesRecyclerViewHolderAPI extends RecyclerView.ViewHolder{

        public MoviesRecyclerViewHolderAPI(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Movie movie){
            ((TextView) itemView.findViewById(R.id.tv_title))
                    .setText(movie.getTitle());
            /*
            ((TextView) itemView.findViewById(R.id.tv_desc))
                    .setText(movie.getDesc());
            */
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(movie);
                }
            });
        }
    }
}