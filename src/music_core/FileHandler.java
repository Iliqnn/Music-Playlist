package music_core;

import music_model.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
/* Отговаря за четенето и записването на данните за песните и плейлистите във външни файлове. */
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
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        Integer.parseInt(data[5]), // year
                        data[6]
                );
                loadedSongs.add(song);
            }
        }
        reader.close();
        return loadedSongs;
    }
}
