import java.util.List;
import java.util.stream.Collectors;

public class PlaysCommand implements Command {
    private MusicManager manager;

    public PlaysCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        String from = null, to = null, playlist = null, songId = null;

        for (String arg : args) {
            if (arg.startsWith("from=")) from = arg.substring(5);
            else if (arg.startsWith("to=")) to = arg.substring(3);
            else if (arg.startsWith("playlist=")) playlist = arg.substring(9);
            else if (arg.startsWith("song=")) songId = arg.substring(5);
        }

        List<PlayEvent> history = manager.getFilteredHistory(from, to);

        final String fPlaylist = playlist;
        final String fSongId = songId;

        List<PlayEvent> filtered = history.stream()
                .filter(e -> (fPlaylist == null || (e.getPlaylistName() != null && e.getPlaylistName().equalsIgnoreCase(fPlaylist))))
                .filter(e -> (fSongId == null || e.getSongId().equalsIgnoreCase(fSongId)))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) return "No plays found for the given criteria.";

        StringBuilder sb = new StringBuilder("Play History:\n");
        for (PlayEvent e : filtered) {
            Song s = manager.findSongById(e.getSongId());
            sb.append("[").append(e.getTimestamp()).append("] ")
                    .append(s.getTitle()).append(e.getPlaylistName() != null ? " from " + e.getPlaylistName() : "")
                    .append("\n");
        }
        return sb.toString().trim();
    }
}
