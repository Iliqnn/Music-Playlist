import java.io.IOException;

public class SaveCommand implements Command {
    private MusicManager manager;
    private FileHandler fileHandler;

    public SaveCommand(MusicManager manager) {
        this.manager = manager;
        this.fileHandler = new FileHandler();
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) {
            return "Error: No file opened to save.";
        }

        try {
            fileHandler.save(manager.getCurrentFileName(), manager.getAllSongs());
            return "Successfully saved to " + manager.getCurrentFileName();
        } catch (IOException e) {
            return "Error: Could not save file.";
        }
    }
}