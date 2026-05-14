import java.time.LocalDateTime;

public class PlayEvent {
    private String songId;
    private String playlistName;
    private LocalDateTime timestamp;

    public PlayEvent(String songId, String playlistName) {
        this.songId = songId;
        this.playlistName = playlistName;
        this.timestamp = LocalDateTime.now();
    }

    public String getSongId() { return songId; }
    public String getPlaylistName() { return playlistName; }
    public LocalDateTime getTimestamp() { return timestamp; }
}