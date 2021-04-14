package com.example.musicdiary.networking;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebHandler {
    private static final String baseURL = "https://www.theaudiodb.com/api/v1/json/1";

    private final Retrofit retrofit;
    private final MusicDiaryAPI musicDiaryAPI;

    public WebHandler() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        musicDiaryAPI = retrofit.create(MusicDiaryAPI.class);
    }

    public void getArtists(String query, Callback<SearchQuery> callback) {
        Call<SearchQuery> call = musicDiaryAPI.getArtists(query);
        call.enqueue(callback);
    }

    public void getAlbums(Integer query, Callback<AlbumsQuery> callback) {
        Call<AlbumsQuery> call = musicDiaryAPI.getAlbums(query);
        call.enqueue(callback);
    }
}
