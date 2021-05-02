package com.example.musicdiary.networking;

import android.os.Build;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Artist {
    @SerializedName("idArtist")
    private final Integer id;

    @SerializedName("strArtist")
    private final String name;

    @SerializedName("intFormedYear")
    private final Integer formedYear;

    @SerializedName("strGenre")
    private final String genre;

    @SerializedName("strBiographyEN")
    private final String description;

    @SerializedName("strArtistThumb")
    private final String picture;

    public Artist(Integer id, String name, Integer formedYear, String genre, String description, String picture){
        this.id = id;
        this.name = name;
        this.formedYear = formedYear;
        this.genre = genre;
        this.description = description;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getFormedYear() {
        return formedYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, name, formedYear, genre, description, picture);
    }
}
