package music_command;

import music_core.MusicManager;
/** Премества песен от един плейлист в друг или променя позицията ѝ. */
public class MoveCommand implements Command {
    private MusicManager manager;

    public MoveCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) return "Error: No file opened.";
        if (args.length < 3) return "Error: Usage: move <playlistName> <fromPos> <toPos>";

        try {
            String playlistName = args[0];
            int fromPos = Integer.parseInt(args[1]);
            int toPos = Integer.parseInt(args[2]);
            return manager.moveSongInPlaylist(playlistName, fromPos, toPos);
        } catch (NumberFormatException e) {
            return "Error: Positions must be numbers.";
        }
    }
}
