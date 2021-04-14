package com.example.musicdiary.networking;

import java.util.List;

public class SearchQuery {
    private final List<Artist> artists;

    public SearchQuery(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Artist> getArtists() {
        return artists;
    }
}
