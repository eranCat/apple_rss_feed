package com.erank.applerssfeed.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.erank.applerssfeed.utils.Media;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
public class Data {

    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "artist_name")
    private String artistName;
    private String name;

    @SerializedName("artworkUrl100")
    @ColumnInfo(name = "artwork_url100")
    private String photoUrl;

    private Genre[] genres;

    @ColumnInfo(name = "release_date")
    private Date releaseDate;

    private String url;
    private Media type;

    public Media getType() {
        return type;
    }

    public void setType(Media type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id='" + id + '\'' +
                ", artistName='" + artistName + '\'' +
                ", name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", releaseDate=" + releaseDate +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return id.equals(data.id) &&
                Objects.equals(artistName, data.artistName) &&
                Objects.equals(name, data.name) &&
                Objects.equals(photoUrl, data.photoUrl) &&
                Arrays.equals(genres, data.genres) &&
                Objects.equals(releaseDate, data.releaseDate) &&
                Objects.equals(url, data.url) &&
                type == data.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, artistName, name, photoUrl, releaseDate, url, type);
        result = 31 * result + Arrays.hashCode(genres);
        return result;
    }
}
