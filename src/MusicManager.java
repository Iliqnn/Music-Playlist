import java.util.ArrayList;
import java.util.List;

public class MusicManager {
    private List<Song> allSongs;
    private List<Playlist> playlists;
    private int songCounter;
    private String currentFileName = null;

    public MusicManager() {
        this.allSongs = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.songCounter = 1;
    }


    public boolean isFileOpened() {
        return currentFileName != null;
    }

    public void setCurrentFileName(String fileName) {
        this.currentFileName = fileName;
    }

    public String getCurrentFileName() {
        return currentFileName;
    }


    public Song findSongById(String id) {
        for (Song s : allSongs) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public void clearData() {
        allSongs.clear();
        playlists.clear();
        currentFileName = null;
        songCounter = 1;
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
