package it.sal.disco.unimib.filmreviewer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.List;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.utils.Constants;

public class MoviesRecyclerViewAdapterLocal extends RecyclerView.Adapter<MoviesRecyclerViewAdapterLocal.MoviesRecyclerViewHolderLocal>{

    public interface OnItemClickListener{
        void onItemClick(Movie movie);
    }

    private OnItemClickListener onItemClickListener;
    private List<Movie> mMoviesList;

    public MoviesRecyclerViewAdapterLocal(List<Movie> mMoviesList, OnItemClickListener OnItemClickListener){
        this.mMoviesList = mMoviesList;
        this.onItemClickListener = OnItemClickListener;
    }

    @NonNull
    @Override
    public MoviesRecyclerViewHolderLocal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_layout_local,
                parent,
                false);
        return new MoviesRecyclerViewHolderLocal(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesRecyclerViewHolderLocal holder, int position) {
        holder.bind(mMoviesList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mMoviesList != null){
            return mMoviesList.size();
        }
        return 0;
    }

    public class MoviesRecyclerViewHolderLocal extends RecyclerView.ViewHolder{

        public MoviesRecyclerViewHolderLocal(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Movie movie){
            //Image Loading
            String link1poster;
            if(movie.getPosters().getPosters().size() > 0){
                link1poster = movie.getPosters().getPosters().get(0).getLink();
            }else{
                link1poster = "";
            }
            ImageView ivBasicImage = itemView.findViewById(R.id.local_img);
            Constants.showImage(10, itemView, link1poster, ivBasicImage);
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(movie));
        }
    }
}
