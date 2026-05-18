package music_main;

import music_command.*;
import music_core.MusicManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/* Входна точка на програмата. Инициализира потребителския интерфейс и обработва командите. */
public class Main {
    public static void main(String[] args) {
        MusicManager manager = new MusicManager();
        Map<String, Command> commands = new HashMap<>();
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("open", new OpenCommand(manager));
        commands.put("close", new CloseCommand(manager));
        commands.put("save", new SaveCommand(manager));
        commands.put("saveas", new SaveAsCommand(manager));
        commands.put("addsong", new AddSongCommand(manager));
        commands.put("listsongs", new ListSongsCommand(manager));
        commands.put("removesong", new RemoveSongCommand(manager));
        commands.put("songinfo", new SongInfoCommand(manager));
        commands.put("createplaylist", new CreatePlaylistCommand(manager));
        commands.put("addtoplaylist", new AddToPlaylistCommand(manager));
        commands.put("removefromplaylist", new RemoveFromPlaylistCommand(manager));
        commands.put("move", new MoveCommand(manager));
        commands.put("showplaylist", new ShowPlaylistCommand(manager));
        commands.put("shuffle", new ShuffleCommand(manager));
        commands.put("dropplaylist", new DropPlaylistCommand(manager));
        commands.put("play", new PlayCommand(manager));
        commands.put("plays", new PlaysCommand(manager));
        commands.put("toptracks", new TopTracksCommand(manager));
        commands.put("topplaylists", new TopPlaylistsCommand(manager));
        commands.put("topartists", new TopArtistsCommand(manager));
        commands.put("lowactivity", new LowActivityCommand(manager));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Music music_model.Playlist System started. Type 'help' for commands.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] tokens = input.split("\\s+");
            String commandName = tokens[0].toLowerCase();

            String[] commandArgs = new String[tokens.length - 1];
            System.arraycopy(tokens, 1, commandArgs, 0, tokens.length - 1);

            if (commands.containsKey(commandName)) {
                String result = commands.get(commandName).execute(commandArgs);

                if (result != null && !result.isEmpty()) {
                    System.out.println(result);
                }

                if (result != null && result.equals("Exiting the program...")) {
                    break;
                }
            } else {
                System.out.println("Error: Unknown command.");
            }
        }
        scanner.close();
    }
}
