package music_command;

import music_core.MusicManager;
import music_model.Song;

import java.util.List;
/** Извежда списък с всички налични песни в библиотеката, заредени от текущия файл. */
public class ListSongsCommand implements Command {
    private MusicManager manager;

    public ListSongsCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) {
            return "Error: No file opened.";
        }

        List<Song> songs = manager.getAllSongs();
        if (songs.isEmpty()) {
            return "The library is empty.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("All songs in library:\n");
        for (Song s : songs) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString().trim();
    }
}
