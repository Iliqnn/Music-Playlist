package music_model;

import java.time.LocalDateTime;
/* Модел, който съхранява информация за конкретно събитие на слушане (дата, час, песен). */
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