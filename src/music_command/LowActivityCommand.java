package music_command;

import music_core.MusicManager;
import music_model.PlayEvent;
import music_model.Playlist;

import java.util.*;
/* Генерира списък с песни или плейлисти, които не са били слушани скоро. */
public class LowActivityCommand implements Command {
    private MusicManager manager;

    public LowActivityCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 3) return "Error: Usage: lowactivity <from> <to> <threshold> [--remove]";

        String from = args[0];
        String to = args[1];
        double threshold = Double.parseDouble(args[2]);
        boolean shouldRemove = args.length > 3 && args[3].equalsIgnoreCase("--remove");

        List<PlayEvent> history = manager.getFilteredHistory(from, to);
        if (history.isEmpty()) return "No activity found for this period.";

        long totalPlays = history.size();
        Map<String, Long> playlistCounts = new HashMap<>();
        for (PlayEvent e : history) {
            if (e.getPlaylistName() != null) {
                playlistCounts.put(e.getPlaylistName(), playlistCounts.getOrDefault(e.getPlaylistName(), 0L) + 1);
            }
        }

        StringBuilder sb = new StringBuilder("Low Activity Report (" + threshold + "% threshold):\n");
        List<String> toDelete = new ArrayList<>();

        for (Playlist p : manager.getPlaylists()) {
            long pPlays = playlistCounts.getOrDefault(p.getName(), 0L);
            double percent = (pPlays * 100.0) / totalPlays;

            if (percent < threshold) {
                sb.append("- ").append(p.getName()).append(": ").append(String.format("%.2f", percent)).append("%\n");
                toDelete.add(p.getName());
            }
        }

        if (shouldRemove && !toDelete.isEmpty()) {
            for (String name : toDelete) {
                manager.getPlaylists().removeIf(p -> p.getName().equalsIgnoreCase(name));
            }
            sb.append("\nTOTAL: ").append(toDelete.size()).append(" playlists were REMOVED.");
        } else if (toDelete.isEmpty()) {
            return "No playlists found below the " + threshold + "% threshold.";
        } else if (!shouldRemove) {
            sb.append("\nTip: Use '--remove' at the end to delete these playlists.");
        }

        return sb.toString().trim();
    }
}
