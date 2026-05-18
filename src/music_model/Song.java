package music_model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/* Представлява модел на музикална песен с информация за заглавие, изпълнител и метаданни. */
public class Song {
    private String id;
    private String title;
    private String artist;
    private String duration;
    private String album;
    private int year;
    private String genre;
    private int playCount;
    private List<LocalDateTime> playHistory;

    public Song(String id, String title, String artist, String duration, String album, int year, String genre) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.playCount = 0;
        this.playHistory = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getDuration() { return duration; }
    public String getAlbum() { return album; }
    public int getYear() { return year; }
    public String getGenre() { return genre; }
    public int getPlayCount() { return playCount; }
    public List<LocalDateTime> getPlayHistory() { return playHistory; }

    public void incrementPlayCount() {
        this.playCount++;
        this.playHistory.add(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return id + ". " + artist + " - " + title + " (" + duration + ") [" + album + ", " + year + "]";
    }
}