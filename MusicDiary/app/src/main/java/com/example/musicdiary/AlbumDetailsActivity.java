package com.example.musicdiary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.musicdiary.networking.Album;
import com.example.musicdiary.networking.Artist;
import com.google.gson.Gson;

public class AlbumDetailsActivity extends AppCompatActivity {
    private Album album;
    private ImageView imageView;
    private TextView genreTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);
        Intent intent = getIntent();
        album = new Gson().fromJson(String.valueOf(intent.getStringExtra("album")), Album.class);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(album.getName());
        }
        imageView = findViewById(R.id.album_image_view);
        genreTextView = findViewById(R.id.album_genre_text_view);
        descriptionTextView = findViewById(R.id.album_description_text_view);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = width;
        imageView.setLayoutParams(params);
        Glide.with(this).load(album.getPicture()).into(imageView);
        genreTextView.setText("Genre: " + album.getGenre() + " " + "Year released: " + album.getYearReleased().toString());
        descriptionTextView.setText(album.getDescription());
    }
}