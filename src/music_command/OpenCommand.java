package music_command;

import music_core.FileHandler;
import music_core.MusicManager;
import music_model.Song;

import java.io.IOException;
import java.util.List;
/** Зарежда музикална библиотека от външен файл и инициализира данните в приложението. */
public class OpenCommand implements Command {
    private MusicManager manager;
    private FileHandler fileHandler;

    public OpenCommand(MusicManager manager) {
        this.manager = manager;
        this.fileHandler = new FileHandler();
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 1) {
            return "Error: Usage: open <fileName>";
        }

        String fileName = args[0];
        try {
            List<Song> loadedSongs = fileHandler.load(fileName);
            manager.clearData();
            for (Song s : loadedSongs) {
                manager.getAllSongs().add(s);
            }
            manager.setCurrentFileName(fileName);
            return "Successfully opened " + fileName;
        } catch (IOException e) {
            manager.clearData();
            manager.setCurrentFileName(fileName);
            return "File not found. Created new empty database in memory: " + fileName;
        }
    }
}
