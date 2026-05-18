public class RemoveFromPlaylistCommand implements Command {
    private MusicManager manager;

    public RemoveFromPlaylistCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) return "Error: No file opened.";
        if (args.length < 2) return "Error: Usage: removefromplaylist <playlistName> <songId>";

        String playlistName = args[0];
        String songId = args[1];

        return manager.removeFromPlaylist(playlistName, songId);
    }
}
