package com.example.musicdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicdiary.networking.Artist;

public class ArtistActivity extends AppCompatActivity {
    Artist artist;
    private ImageView imageView;
    private TextView textView;
    private Button favouriteButton;
    private Button unfavouriteButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        imageView = findViewById(R.id.artist_image_view2);
        textView = findViewById(R.id.artist_name_text_view);
        favouriteButton = findViewById(R.id.add_to_favourite_button);
        unfavouriteButton = findViewById(R.id.remove_favourite_button);
        recyclerView = findViewById(R.id.discography_recycler_view);

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        unfavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}