package music_command;
/** Базов интерфейс, който дефинира структурата за всички изпълними команди. */
public interface Command
{
    String execute(String[] args);
}
