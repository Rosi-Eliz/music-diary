package com.example.musicdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicdiary.networking.Album;
import com.example.musicdiary.networking.AlbumsQuery;
import com.example.musicdiary.networking.Artist;
import com.google.gson.Gson;

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
        private ConstraintLayout containerLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.album_image_view);
            imageView.setImageResource(R.drawable.placeholder_image);
            name = itemView.findViewById(R.id.album);
            containerLayout = itemView.findViewById(R.id.album_item_container);

            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;

            ViewGroup.LayoutParams params = containerLayout.getLayoutParams();
            params.height = width / 3;
            containerLayout.setLayoutParams(params);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer id = getAdapterPosition();
                    Album album = albumsQuery.getAlbums().get(id);
                    Intent intent = new Intent(context, AlbumDetailsActivity.class);
                    intent.putExtra("album", new Gson().toJson(album));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
