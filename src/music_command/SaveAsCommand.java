package music_command;

import music_core.FileHandler;
import music_core.MusicManager;

import java.io.IOException;
/* Позволява на потребителя да запише текущото състояние на данните в нов файл с посочено от него име. */
public class SaveAsCommand implements Command {
    private MusicManager manager;
    private FileHandler fileHandler;

    public SaveAsCommand(MusicManager manager) {
        this.manager = manager;
        this.fileHandler = new FileHandler();
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) {
            return "Error: No file opened.";
        }

        if (args.length < 1) {
            return "Error: Usage: saveas <fileName>";
        }

        String newFileName = args[0];
        try {
            fileHandler.save(newFileName, manager.getAllSongs());
            manager.setCurrentFileName(newFileName);
            return "Successfully saved as " + newFileName;
        } catch (IOException e) {
            return "Error: Could not save file.";
        }
    }
}