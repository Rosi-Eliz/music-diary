package com.example.musicdiary.networking;

import com.google.gson.annotations.SerializedName;

public class Album {
    @SerializedName("idAlbum")
    private final Integer id;

    @SerializedName("strAlbum")
    private final String name;

    @SerializedName("intYearReleased")
    private final Integer yearReleased;

    @SerializedName("strGenre")
    private final String genre;

    @SerializedName("strDescriptionEN")
    private final String description;

    @SerializedName("strAlbumThumb")
    private final String picture;

    public Album(Integer id, String name, Integer yearReleased, String genre, String description, String picture) {
        this.id = id;
        this.name = name;
        this.yearReleased = yearReleased;
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

    public Integer getYearReleased() {
        return yearReleased;
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
}
