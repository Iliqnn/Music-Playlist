import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Music Playlist System started. Type 'help' for commands.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

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
                System.out.println("Error: Unknown command. Type 'help' for assistance.");
            }
        }

        scanner.close();
    }
}
