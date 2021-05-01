package com.example.musicdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicdiary.networking.Album;
import com.example.musicdiary.networking.AlbumsQuery;
import com.example.musicdiary.networking.Artist;
import com.example.musicdiary.networking.SearchQuery;

public class AlbumbsViewAdapter extends RecyclerView.Adapter<AlbumbsViewAdapter.ViewHolder> {

    private Context context;
    AlbumsQuery albumsQuery;

    public AlbumbsViewAdapter(Context context, AlbumsQuery albumsQuery) {
        this.context = context;
        this.albumsQuery = albumsQuery;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.album_item, parent, false);
        return new AlbumbsViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumsQuery.getAlbums().get(position);
        Glide.with(context).load(album.getPicture()).into(holder.imageView);
        holder.name.setText(album.getName());
        if(album.getYearReleased() != null) {
            holder.year.setText(album.getYearReleased().toString());
        }
        holder.genre.setText(album.getGenre());
        holder.description.setText(album.getDescription());
    }

    @Override
    public int getItemCount() {
        if(albumsQuery.getAlbums() == null)
        {
            Toast.makeText(context, "No discography found.", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return albumsQuery.getAlbums().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private TextView year;
        private TextView genre;
        private TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.album_image_view);
            name = itemView.findViewById(R.id.album);
            year = itemView.findViewById(R.id.year_released_text_view);
            genre = itemView.findViewById(R.id.genre_text_view);
            description = itemView.findViewById(R.id.description_text_view);
        }

    }
}
