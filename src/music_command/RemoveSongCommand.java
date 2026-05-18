package music_command;

import music_core.MusicManager;
import music_model.Song;

import java.util.List;
/** Премахва песен от главната библиотека и от всички плейлисти, в които тя участва. */
public class RemoveSongCommand implements Command {
    private MusicManager manager;

    public RemoveSongCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) {
            return "Error: No file opened.";
        }

        if (args.length < 1) {
            return "Error: Usage: removesong <songId>";
        }

        String id = args[0];
        List<Song> songs = manager.getAllSongs();
        boolean removed = songs.removeIf(s -> s.getId().equalsIgnoreCase(id));

        if (removed) {
            return "Successfully removed song with ID " + id;
        } else {
            return "Error: music_model.Song with ID " + id + " not found.";
        }
    }
}