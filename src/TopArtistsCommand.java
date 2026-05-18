import java.util.*;
import java.util.stream.Collectors;

public class TopArtistsCommand implements Command {
    private MusicManager manager;

    public TopArtistsCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 1) return "Error: Usage: topartists <n> [from=date] [to=date]";

        int n = Integer.parseInt(args[0]);
        String from = null, to = null;
        for (String arg : args) {
            if (arg.startsWith("from=")) from = arg.substring(5);
            if (arg.startsWith("to=")) to = arg.substring(3);
        }

        List<PlayEvent> history = manager.getFilteredHistory(from, to);


        Map<String, Long> artistCounts = new HashMap<>();
        for (PlayEvent event : history) {
            Song song = manager.findSongById(event.getSongId());
            if (song != null) {
                artistCounts.put(song.getArtist(), artistCounts.getOrDefault(song.getArtist(), 0L) + 1);
            }
        }

        List<Map.Entry<String, Long>> sorted = artistCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder("Top " + n + " Artists:\n");
        for (Map.Entry<String, Long> entry : sorted) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" total plays\n");
        }
        return sb.toString().trim();
    }
}