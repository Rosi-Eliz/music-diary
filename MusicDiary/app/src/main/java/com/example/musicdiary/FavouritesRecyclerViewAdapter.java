package com.example.musicdiary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicdiary.networking.Artist;
import com.google.gson.Gson;

import java.util.List;

public class FavouritesRecyclerViewAdapter extends RecyclerView.Adapter<FavouritesRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Artist> artists;
    public FavouritesRecyclerViewAdapter(Context context, List<Artist> artists) {
        this.context = context;
        this.artists = artists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.artist_item, parent, false);
        return new FavouritesRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artist artist = artists.get(position);
        Glide.with(context).load(artist.getPicture()).into(holder.imageView);
        holder.textView.setText(artist.getName());
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.artist_image_view);
            textView = itemView.findViewById(R.id.artist_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer id = getAdapterPosition();
                    Artist artist =  artists.get(id);
                    Intent intent = new Intent(context, ArtistActivity.class);
                    intent.putExtra("artist", new Gson().toJson(artist));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
