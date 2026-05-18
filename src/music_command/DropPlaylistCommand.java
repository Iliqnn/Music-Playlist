package music_command;

import music_core.MusicManager;
/** Изтрива целия плейлист от системата. */
public class DropPlaylistCommand implements Command {
    private MusicManager manager;

    public DropPlaylistCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 1) return "Error: Usage: dropplaylist <name>";

        String name = args[0];
        boolean removed = manager.getPlaylists().removeIf(p -> p.getName().equalsIgnoreCase(name));

        return removed ? "music_model.Playlist '" + name + "' deleted." : "Error: music_model.Playlist not found.";
    }
}
