package music_command;

import music_core.MusicManager;
import music_model.Song;
/** Извлича и показва детайлна информация за конкретна песен и статистика на слушане. */
public class SongInfoCommand implements Command {
    private MusicManager manager;

    public SongInfoCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) {
            return "Error: No file opened.";
        }

        if (args.length < 1) {
            return "Error: Usage: songinfo <songId>";
        }

        Song song = manager.findSongById(args[0]);
        if (song == null) {
            return "Error: music_model.Song not found.";
        }

        return "Detailed Info:\n" +
                "ID: " + song.getId() + "\n" +
                "Title: " + song.getTitle() + "\n" +
                "Artist: " + song.getArtist() + "\n" +
                "Album: " + song.getAlbum() + "\n" +
                "Duration: " + song.getDuration() + "\n" +
                "Year: " + song.getYear() + "\n" +
                "Genre: " + song.getGenre() + "\n" +
                "Plays: " + song.getPlayCount();
    }
}
