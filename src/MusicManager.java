import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MusicManager {
    private List<Song> allSongs;
    private List<Playlist> playlists;
    private int songCounter;
    private String currentFileName = null;
    private List<PlayEvent> playHistory = new ArrayList<>();

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

    public List<Song> getAllSongs() {
        return allSongs;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
    public List<PlayEvent> getPlayHistory() { return playHistory; }


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

    public List<Song> getSongsByArtist(String artist) {
        List<Song> result = new ArrayList<>();
        for (Song s : allSongs) {
            if (s.getArtist().equalsIgnoreCase(artist)) {
                result.add(s);
            }
        }
        return result;
    }

    public String createPlaylist(String name, String description) {
        for (Playlist p : playlists) {
            if (p.getName().equalsIgnoreCase(name)) {
                return "Error: Playlist '" + name + "' already exists.";
            }
        }
        playlists.add(new Playlist(name, description));
        return "Success: Playlist '" + name + "' created.";
    }

    public String addSongToPlaylist(String playlistName, String songId, Integer position) {
        Playlist targetPlaylist = null;
        for (Playlist p : playlists) {
            if (p.getName().equalsIgnoreCase(playlistName)) {
                targetPlaylist = p;
                break;
            }
        }

        if (targetPlaylist == null) {
            return "Error: Playlist '" + playlistName + "' not found.";
        }

        Song targetSong = findSongById(songId);
        if (targetSong == null) {
            return "Error: Song with ID '" + songId + "' not found.";
        }

        if (position != null) {
            targetPlaylist.addSongAt(targetSong, position);
            return "Success: Added " + targetSong.getTitle() + " to " + playlistName + " at position " + position;
        } else {
            targetPlaylist.addSong(targetSong);
            return "Success: Added " + targetSong.getTitle() + " to " + playlistName;
        }
    }

    public String removeFromPlaylist(String playlistName, String songId) {
        Playlist p = findPlaylistByName(playlistName);
        if (p == null) return "Error: Playlist not found.";

        boolean removed = p.getSongs().removeIf(s -> s.getId().equalsIgnoreCase(songId));
        return removed ? "Song removed from " + playlistName : "Song not found in playlist.";
    }

    public String moveSongInPlaylist(String playlistName, int from, int to) {
        Playlist p = findPlaylistByName(playlistName);
        if (p == null) return "Error: Playlist not found.";

        List<Song> s = p.getSongs();
        if (from < 0 || from >= s.size() || to < 0 || to >= s.size()) {
            return "Error: Invalid positions.";
        }

        Song song = s.remove(from);
        s.add(to, song);
        return "Moved " + song.getTitle() + " from position " + from + " to " + to;
    }
    public int parseDurationToSeconds(String duration) {
        try {
            String[] parts = duration.split(":");
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            return (minutes * 60) + seconds;
        } catch (Exception e) {
            return 0;
        }
    }

    public String formatSecondsToDuration(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private Playlist findPlaylistByName(String name) {
        return playlists.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public String playSong(String songId, String playlistName) {
        Song song = findSongById(songId);
        if (song == null) return "Error: Song not found.";

        if (playlistName != null) {
            Playlist p = findPlaylistByName(playlistName);
            if (p == null) return "Error: Playlist not found.";
            if (p.getSongs().stream().noneMatch(s -> s.getId().equals(songId))) {
                return "Error: Song not in playlist " + playlistName;
            }
        }
        playHistory.add(new PlayEvent(songId, playlistName));
        song.incrementPlayCount();

        return "Playing: " + song.getTitle() + " by " + song.getArtist() +
                (playlistName != null ? " (from " + playlistName + ")" : "");
    }
    public List<PlayEvent> getFilteredHistory(String fromDate, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = (fromDate != null) ? LocalDate.parse(fromDate, formatter) : LocalDate.MIN;
        LocalDate end = (toDate != null) ? LocalDate.parse(toDate, formatter) : LocalDate.MAX;

        return playHistory.stream()
                .filter(e -> {
                    LocalDate eventDate = e.getTimestamp().toLocalDate();
                    return !eventDate.isBefore(start) && !eventDate.isAfter(end);
                })
                .collect(Collectors.toList());
    }
}