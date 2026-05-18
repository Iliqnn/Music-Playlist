package music_command;

import music_core.MusicManager;
/* Команда за създаване на нов празен плейлист. */
public class CreatePlaylistCommand implements Command {
    private MusicManager manager;

    public CreatePlaylistCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) {
            return "Error: No file opened.";
        }

        if (args.length < 1) {
            return "Error: Usage: createplaylist <name> [description]";
        }

        String name = args[0];
        String description = args.length > 1 ? args[1] : "No description";

        return manager.createPlaylist(name, description);
    }
}