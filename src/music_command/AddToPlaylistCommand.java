package music_command;

import music_core.MusicManager;
/** Добавя съществуваща песен от библиотеката към избран плейлист. */
public class AddToPlaylistCommand implements Command {
    private MusicManager manager;

    public AddToPlaylistCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) {
            return "Error: No file opened.";
        }

        if (args.length < 2) {
            return "Error: Usage: addtoplaylist <playlistName> <songId> [pos=<n>]";
        }

        String playlistName = args[0];
        String songId = args[1];
        Integer position = null;

        if (args.length > 2 && args[2].startsWith("pos=")) {
            try {
                position = Integer.parseInt(args[2].substring(4));
            } catch (NumberFormatException e) {
                return "Error: Position must be a number.";
            }
        }

        return manager.addSongToPlaylist(playlistName, songId, position);
    }
}
