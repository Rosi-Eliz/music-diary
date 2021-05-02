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

import java.util.zip.Inflater;

import retrofit2.Call;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {

    private Context context;
    SearchQuery searchQuery;

    public SearchRecyclerViewAdapter(Context context, SearchQuery searchQuery) {
        this.context = context;
        this.searchQuery = searchQuery;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.artist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artist artist = searchQuery.getArtists().get(position);
        Glide.with(context).load(artist.getPicture()).into(holder.imageView);
        holder.textView.setText(artist.getName());
    }

    @Override
    public int getItemCount() {
        if(searchQuery.getArtists() == null)
        {
            Toast.makeText(context, "No artists found.", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return searchQuery.getArtists().size();
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
                    Artist artist = searchQuery.getArtists().get(id);
                    Intent intent = new Intent(context, ArtistActivity.class);
                    intent.putExtra("artist", new Gson().toJson(artist));
                    itemView.getContext().startActivity(intent);
                }
            });
        }

    }
}
