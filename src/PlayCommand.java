public class PlayCommand implements Command {
    private MusicManager manager;

    public PlayCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) return "Error: No file opened.";
        if (args.length < 1) return "Error: Usage: play <songId> [playlist=<name>]";

        String songId = args[0];
        String playlistName = null;

        if (args.length > 1 && args[1].startsWith("playlist=")) {
            playlistName = args[1].substring(9);
        }

        return manager.playSong(songId, playlistName);
    }
}
