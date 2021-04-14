package com.example.musicdiary.networking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumsQuery {
    @SerializedName("album")
    private final List<Album> albums;

    public AlbumsQuery(List<Album> albums) {
        this.albums = albums;
    }

    public List<Album> getAlbums() {
        return albums;
    }
}
