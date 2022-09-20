package com.jukebox.data;

import java.sql.Time;

public class Song {

    public Song() {
    }

    public Song(int song_id, String song_name, Time song_duration, String genres, String singer_name, int album_id, String album_name) {

    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    private int songId;
    private String songTitle;
    private String genre;
    private String album;
    private String artist;
    private String duration;

    public Song(int songId, String songTitle, String genre, String album, String artist, String duration) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songTitle='" + songTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

}
