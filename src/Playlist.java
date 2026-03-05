import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private String description;
    private List<Song> songs;

    public Playlist(String name, String description) {
        this.name = name;
        this.description = description;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public String getName() { return name; }
    public List<Song> getSongs() { return songs; }
    public String getDescription() { return description; }
}