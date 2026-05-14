public class DropPlaylistCommand implements Command {
    private MusicManager manager;

    public DropPlaylistCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 1) return "Error: Usage: dropplaylist <name>";

        String name = args[0];
        boolean removed = manager.getPlaylists().removeIf(p -> p.getName().equalsIgnoreCase(name));

        return removed ? "Playlist '" + name + "' deleted." : "Error: Playlist not found.";
    }
}
