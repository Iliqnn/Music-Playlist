import java.io.IOException;

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