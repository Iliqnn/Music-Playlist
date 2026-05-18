package music_command;

import music_core.MusicManager;
import music_model.PlayEvent;
import music_model.Song;

import java.util.*;
import java.util.stream.Collectors;
/** Команда за генериране на статистика за най-слушаните песни. */
public class TopTracksCommand implements Command {
    private MusicManager manager;

    public TopTracksCommand(MusicManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 1) return "Error: Usage: toptracks <n> [from=date] [to=date]";

        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            return "Error: <n> must be a number.";
        }

        String from = null, to = null;
        for (String arg : args) {
            if (arg.startsWith("from=")) from = arg.substring(5);
            if (arg.startsWith("to=")) to = arg.substring(3);
        }


        List<PlayEvent> history = manager.getFilteredHistory(from, to);

        if (history.isEmpty()) return "No play history found for this period.";


        Map<String, Long> counts = history.stream()
                .collect(Collectors.groupingBy(PlayEvent::getSongId, Collectors.counting()));


        List<Map.Entry<String, Long>> sorted = counts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder("Top " + n + " Tracks:\n");
        for (Map.Entry<String, Long> entry : sorted) {
            Song s = manager.findSongById(entry.getKey());
            if (s != null) {
                sb.append(s.getTitle()).append(" - ").append(entry.getValue()).append(" plays\n");
            }
        }
        return sb.toString().trim();
    }
}
