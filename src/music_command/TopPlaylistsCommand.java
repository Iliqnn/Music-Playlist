package music_command;

import music_core.MusicManager;
import music_model.PlayEvent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/* Показва най-популярните плейлисти според общото време на слушане или брой пускания. */
public class TopPlaylistsCommand implements Command {
    private MusicManager manager;

    public TopPlaylistsCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 1) return "Error: Usage: topplaylists <n> [from=date] [to=date]";

        int n = Integer.parseInt(args[0]);
        String from = null, to = null;
        for (String arg : args) {
            if (arg.startsWith("from=")) from = arg.substring(5);
            if (arg.startsWith("to=")) to = arg.substring(3);
        }

        List<PlayEvent> history = manager.getFilteredHistory(from, to);

        Map<String, Long> playlistCounts = history.stream()
                .filter(e -> e.getPlaylistName() != null)
                .collect(Collectors.groupingBy(PlayEvent::getPlaylistName, Collectors.counting()));

        List<Map.Entry<String, Long>> sorted = playlistCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder("Top " + n + " Active Playlists:\n");
        for (Map.Entry<String, Long> entry : sorted) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" plays\n");
        }
        return sb.toString().trim();
    }
}
