public class CloseCommand implements Command {
    private MusicManager manager;

    public CloseCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) {
            return "Error: No file is currently opened.";
        }

        String fileName = manager.getCurrentFileName();
        manager.clearData();
        return "Successfully closed " + fileName;
    }
}
