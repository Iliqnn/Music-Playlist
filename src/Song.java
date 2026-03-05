public class Song {
    private String id;
    private String title;
    private String artist;
    private String duration;
    private String album;
    private int year;
    private String genre;


    public Song(String id, String title, String artist, String duration, String album, int year, String genre) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
        this.year = year;
        this.genre = genre;
    }


    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return id + ". " + artist + " - " + title + " (" + duration + ") [" + album + ", " + year + "]";
    }
}