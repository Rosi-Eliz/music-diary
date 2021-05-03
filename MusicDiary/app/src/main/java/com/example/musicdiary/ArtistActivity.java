package com.example.musicdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.musicdiary.networking.AlbumsQuery;
import com.example.musicdiary.networking.Artist;
import com.example.musicdiary.networking.WebHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistActivity extends AppCompatActivity {
    Artist artist;
    private TextView descriptionTextView;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private WebHandler webHandler;
    private Menu menu;
    private ProgressBar progressBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.artist_menu, menu);
        this.menu = menu;
        styleFavouriteButton();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.favourite_button) {
            if(isArtistFavourite()) {
                removeArtistFromFavourites();
                Toast.makeText(this, "Artist removed from favourites", Toast.LENGTH_SHORT).show();
            } else {
                addArtistToFavourites();
                Toast.makeText(this, "Artist added to favourites", Toast.LENGTH_SHORT).show();
            }
            styleFavouriteButton();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        imageView = findViewById(R.id.artist_image_view2);
        descriptionTextView = findViewById(R.id.artist_description_text_view);
        recyclerView = findViewById(R.id.discography_recycler_view);
        progressBar = findViewById(R.id.artist_progress_bar);

        Intent intent = getIntent();
        artist = new Gson().fromJson(String.valueOf(intent.getStringExtra("artist")), Artist.class);
        descriptionTextView.setText(artist.getDescription());
        descriptionTextView.setMovementMethod(new ScrollingMovementMethod());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(artist.getName());
        }

        webHandler = new WebHandler();
        Glide.with(this).load(artist.getPicture()).into(imageView);
        progressBar.setVisibility(View.VISIBLE);
        webHandler.getAlbums(artist.getId(), new Callback<AlbumsQuery>() {
            @Override
            public void onResponse(Call<AlbumsQuery> call, Response<AlbumsQuery> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if(response.body() == null)
                    return;

                AlbumbsViewAdapter albumbsViewAdapter = new AlbumbsViewAdapter(ArtistActivity.this, response.body());
                recyclerView.setLayoutManager(new LinearLayoutManager(ArtistActivity.this));
                recyclerView.setAdapter(albumbsViewAdapter);
            }

            @Override
            public void onFailure(Call<AlbumsQuery> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(ArtistActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isArtistFavourite() {
        SharedPreferences settings;
        settings = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);

        String currentArtist = settings.getString("artists", "");
        List<Artist> favouriteArtists = new ArrayList<>();
        Type listType = new TypeToken<ArrayList<Artist>>(){}.getType();

        if(!currentArtist.isEmpty()){

            favouriteArtists = new Gson().fromJson(currentArtist, listType);
            if(favouriteArtists.contains(artist)) {
                return true;
            }
        }
        return false;
    }

    private void addArtistToFavourites() {
        SharedPreferences settings;
        settings = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);

        String currentArtist = settings.getString("artists", "");
        List<Artist> favouriteArtists = new ArrayList<>();
        Type listType = new TypeToken<ArrayList<Artist>>(){}.getType();

        if(!currentArtist.isEmpty()){

            favouriteArtists = new Gson().fromJson(currentArtist, listType);
            if(favouriteArtists.contains(artist))
                return;
        }
        favouriteArtists.add(artist);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("artists", new Gson().toJson(favouriteArtists));
        editor.commit();
    }

    private void removeArtistFromFavourites() {
        SharedPreferences settings;
        settings = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);

        String currentArtist = settings.getString("artists", "");
        List<Artist> favouriteArtists = new ArrayList<>();
        Type listType = new TypeToken<ArrayList<Artist>>(){}.getType();

        favouriteArtists = new Gson().fromJson(currentArtist, listType);
        if(favouriteArtists.contains(artist)){
            favouriteArtists.remove(artist);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("artists", new Gson().toJson(favouriteArtists));
            editor.commit();
        }
    }

    private void styleFavouriteButton() {
        if (menu == null) {
            return;
        }

        if (!isArtistFavourite()) {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favourite_border));
        } else {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favourite));
        }
    }
}