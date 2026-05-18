import java.util.List;

public class ShowPlaylistCommand implements Command {
    private MusicManager manager;

    public ShowPlaylistCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) {
            return "Error: No file opened.";
        }

        if (args.length < 1) {
            return "Error: Usage: showplaylist <name>";
        }

        String name = args[0];
        Playlist p = null;
        for (Playlist pl : manager.getPlaylists()) {
            if (pl.getName().equalsIgnoreCase(name)) {
                p = pl;
                break;
            }
        }

        if (p == null) {
            return "Error: Playlist not found.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Playlist: ").append(p.getName()).append("\n");
        sb.append("Description: ").append(p.getDescription()).append("\n");

        List<Song> songs = p.getSongs();
        int totalSeconds = 0;

        if (songs.isEmpty()) {
            sb.append("This playlist is empty.");
        } else {
            for (int i = 0; i < songs.size(); i++) {
                Song s = songs.get(i);
                sb.append(i + 1).append(". ").append(s.toString()).append("\n");
                totalSeconds += manager.parseDurationToSeconds(s.getDuration());
            }
            sb.append("\nTotal Playlist Duration: ").append(manager.formatSecondsToDuration(totalSeconds));
        }

        return sb.toString().trim();
    }
}