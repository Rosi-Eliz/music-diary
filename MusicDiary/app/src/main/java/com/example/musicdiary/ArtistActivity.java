package com.example.musicdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.musicdiary.networking.AlbumsQuery;
import com.example.musicdiary.networking.Artist;
import com.example.musicdiary.networking.WebHandler;
import com.google.android.material.tabs.TabLayoutMediator;
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
    private ImageView imageView;
    private TextView textView;
    private Button favouriteButton;
    private Button unfavouriteButton;
    private RecyclerView recyclerView;
    private WebHandler webHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        imageView = findViewById(R.id.artist_image_view2);
        textView = findViewById(R.id.artist_name_text_view);
        favouriteButton = findViewById(R.id.add_to_favourite_button);
        unfavouriteButton = findViewById(R.id.remove_favourite_button);
        recyclerView = findViewById(R.id.discography_recycler_view);
        webHandler = new WebHandler();
        Intent intent = getIntent();
//        intent.putExtra("artist", new Gson().toJson(artist));
        Artist artist = new Gson().fromJson(String.valueOf(intent.getStringExtra("artist")), Artist.class);
        textView.setText(artist.getName());
        Glide.with(this).load(artist.getPicture()).into(imageView);
        webHandler.getAlbums(artist.getId(), new Callback<AlbumsQuery>() {
            @Override
            public void onResponse(Call<AlbumsQuery> call, Response<AlbumsQuery> response) {
                if(response.body() == null)
                    return;

                AlbumbsViewAdapter albumbsViewAdapter = new AlbumbsViewAdapter(ArtistActivity.this, response.body());
                recyclerView.setLayoutManager(new LinearLayoutManager(ArtistActivity.this));
                recyclerView.setAdapter(albumbsViewAdapter);
            }


            @Override
            public void onFailure(Call<AlbumsQuery> call, Throwable t) {

            }
        });

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        unfavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }
}