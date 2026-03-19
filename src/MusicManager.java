import java.util.ArrayList;
import java.util.List;

public class MusicManager {
    private List<Song> allSongs;
    private List<Playlist> playlists;
    private int songCounter;

    public MusicManager() {
        this.allSongs = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.songCounter = 1;
    }

    public String addSong(String title, String artist, String duration, String album, int year, String genre) {
        for (Song s : allSongs) {
            if (s.getTitle().equalsIgnoreCase(title) && s.getArtist().equalsIgnoreCase(artist)) {
                return "Error: Song '" + title + "' by " + artist + " already exists.";
            }
        }

        String id = "S" + songCounter++;
        allSongs.add(new Song(id, title, artist, duration, album, year, genre));
        return "Success: Added song with ID " + id;
    }

    public List<Song> getAllSongs() {
        return allSongs;
    }

    public List<Song> getSongsByArtist(String artist) {
        List<Song> result = new ArrayList<>();
        for (Song s : allSongs) {
            if (s.getArtist().equalsIgnoreCase(artist)) {
                result.add(s);
            }
        }
        return result;
    }
}
