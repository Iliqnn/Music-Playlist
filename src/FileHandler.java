import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public void save(String fileName, List<Song> songs) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));
        for (Song s : songs) {
            writer.println(s.getId() + "," +
                    s.getTitle() + "," +
                    s.getArtist() + "," +
                    s.getDuration() + "," +
                    s.getAlbum() + "," +
                    s.getYear() + "," +
                    s.getGenre());
        }
        writer.close();
    }

    public List<Song> load(String fileName) throws IOException {
        List<Song> loadedSongs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 7) {
                Song song = new Song(
                        data[0], // id
                        data[1], // title
                        data[2], // artist
                        data[3], // duration
                        data[4], // album
                        Integer.parseInt(data[5]), // year
                        data[6]  // genre
                );
                loadedSongs.add(song);
            }
        }
        reader.close();
        return loadedSongs;
    }
}
