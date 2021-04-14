package com.example.musicdiary.networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;

public interface MusicDiaryAPI {
    @GET("search.php")
    Call<SearchQuery> getArtists(@Query("s") String query);

    @GET("album.php")
    Call<AlbumsQuery> getAlbums(@Query("i") Integer query);
}
