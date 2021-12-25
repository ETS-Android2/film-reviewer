package it.sal.disco.unimib.filmreviewer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.sal.disco.unimib.filmreviewer.R;
import it.sal.disco.unimib.filmreviewer.customObj.Actor;

public class ActorsRecyclerViewAdatper extends RecyclerView.Adapter<ActorsRecyclerViewAdatper.ActorsRecyclerViewHolder>{

    public interface OnItemClickListener{
        void onItemClick(Actor actor);
    }

    private List<Actor> mActorList;
    private ActorsRecyclerViewAdatper.OnItemClickListener onItemClickListener;

    public ActorsRecyclerViewAdatper(List<Actor> mActorList, OnItemClickListener onItemClickListener) {
        this.mActorList = mActorList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ActorsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.actor_layout_edit,
                parent,
                false);
        return new ActorsRecyclerViewAdatper.ActorsRecyclerViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorsRecyclerViewHolder holder, int position) {
        holder.bind(mActorList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mActorList != null){
            return mActorList.size();
        }
        return 0;
    }

    public class ActorsRecyclerViewHolder extends RecyclerView.ViewHolder{

        public ActorsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Actor actor){
            /*
            //Text Setting
            ((TextView) itemView.findViewById(R.id.tv_title))
                    .setText(movie.getTitle());
            ((TextView) itemView.findViewById(R.id.tv_desc))
                    .setText(String.valueOf(movie.getTitle2()));

            //Image Loading
            String imageUri = Constants.getLittleImage(movie.getImage());
            ImageView ivBasicImage = itemView.findViewById(R.id.tv_imgv);
            int[] size = Constants.imagePosterSize(5);
            Picasso.get()
                    .load(imageUri)
                    .resize(size[0], size[1])
                    .centerCrop()
                    .into(ivBasicImage);

             */
            ((TextView) itemView.findViewById(R.id.actorName))
                    .setText(actor.getName());
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(actor));
        }
    }
}
