package com.example.musicdiary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicdiary.networking.Artist;
import com.example.musicdiary.networking.SearchQuery;
import com.google.gson.Gson;

import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    List<Artist> artists;

    public SearchRecyclerViewAdapter(Context context, List<Artist> artists) {
        this.context = context;
        this.artists = artists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        if(artists == null || artists.isEmpty()) {
            view = layoutInflater.inflate(R.layout.empty_item, parent, false);
            return new EmptyViewHolder(view);
        } else {
            view = layoutInflater.inflate(R.layout.artist_item, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(artists != null && !artists.isEmpty()) {
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            Artist artist = artists.get(position);
            Glide.with(context).load(artist.getPicture()).into(itemViewHolder.imageView);
            itemViewHolder.textView.setText(artist.getName());
        }
    }
    
    @Override
    public int getItemCount() {
        if(artists == null || artists.size() == 0)
        {
            return 1;
        }
        return artists.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.artist_image_view);
            imageView.setImageResource(R.drawable.placeholder_image);
            textView = itemView.findViewById(R.id.artist_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer id = getAdapterPosition();
                    Artist artist = artists.get(id);
                    Intent intent = new Intent(context, ArtistActivity.class);
                    intent.putExtra("artist", new Gson().toJson(artist));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    public class EmptyViewHolder extends  RecyclerView.ViewHolder {

        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
