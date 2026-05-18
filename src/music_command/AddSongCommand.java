package music_command;

import music_core.MusicManager;
/** Команда за добавяне на нова песен към общата библиотека. */
public class AddSongCommand implements Command {
    private MusicManager manager;

    public AddSongCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (!manager.isFileOpened()) {
            return "Error: No file opened. Please open a file first.";
        }

        if (args.length < 6) {
            return "Error: Invalid arguments. Usage: addsong <title> <artist> <duration> <album> <year> <genre>";
        }

        try {
            String title = args[0];
            String artist = args[1];
            String duration = args[2];
            String album = args[3];
            int year = Integer.parseInt(args[4]);
            String genre = args[5];

            return manager.addSong(title, artist, duration, album, year, genre);
        } catch (NumberFormatException e) {
            return "Error: Year must be a number.";
        }
    }
}
