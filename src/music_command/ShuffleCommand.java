package music_command;

import music_core.MusicManager;
import music_model.Playlist;

import java.util.Collections;
import java.util.Random;
/* Команда за разбъркване на реда на песните в даден плейлист. */
public class ShuffleCommand implements Command {
    private MusicManager manager;

    public ShuffleCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) return "Error: No file opened.";
        if (args.length < 1) return "Error: Usage: shuffle <playlistName> [seed=<n>]";

        String playlistName = args[0];
        Playlist p = manager.getPlaylists().stream()
                .filter(pl -> pl.getName().equalsIgnoreCase(playlistName))
                .findFirst().orElse(null);

        if (p == null) return "Error: music_model.Playlist not found.";

        if (args.length > 1 && args[1].startsWith("seed=")) {
            long seed = Long.parseLong(args[1].substring(5));
            Collections.shuffle(p.getSongs(), new Random(seed));
        } else {
            Collections.shuffle(p.getSongs());
        }

        return "music_model.Playlist '" + playlistName + "' shuffled.";
    }
}