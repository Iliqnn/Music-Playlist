public class HelpCommand implements Command {
    @Override
    public String execute(String[] args) {
        return "The following commands are supported:\n" +
                "--------------------------------------------------------------------------------------\n" +
                "General Commands:\n" +
                "  open <file>                     opens <file>\n" +
                "  close                           closes currently opened file\n" +
                "  save                            saves the currently open file\n" +
                "  saveas <file>                   saves the currently open file in <file>\n" +
                "  help                            prints this information\n" +
                "  exit                            exists the program\n\n" +

                "Song Management:\n" +
                "  addsong <title> <artist> <duration> <album> <year> <genre>\n" +
                "                                  adds a new song (use quotes for names with spaces)\n" +
                "  listsongs                       lists all songs in the system\n" +
                "  removesong <songId>             removes a song by ID\n" +
                "  songinfo <songId>               shows detailed info for a song\n\n" +

                "Playlist Management:\n" +
                "  createplaylist <name> <desc>    creates a new playlist with description\n" +
                "  addtoplaylist <pl> <id> [pos=n] adds a song to a playlist\n" +
                "  removefromplaylist <pl> <id>    removes a song from a playlist\n" +
                "  move <pl> <from> <to>           moves a song within a playlist\n" +
                "  showplaylist <name>             shows content and total duration\n" +
                "  shuffle <name> [seed=n]         shuffles songs in a playlist\n" +
                "  dropplaylist <name>             removes a playlist from the system\n\n" +

                "Playback & Statistics:\n" +
                "  play <id> [playlist=name]       plays a song and records date/time\n" +
                "  plays [from=d] [to=d] [song=id] shows play history filtered by criteria\n" +
                "  toptracks <n> [from=d] [to=d]   shows top n most played songs\n" +
                "  topplaylists <n> [from=d] [to=d] shows top n active playlists\n" +
                "  topartists <n> [from=d] [to=d]  shows top n artists by total plays\n" +
                "  lowactivity <f> <t> <%> [--remove] shows/removes playlists with low activity\n" +
                "--------------------------------------------------------------------------------------";
    }
}
