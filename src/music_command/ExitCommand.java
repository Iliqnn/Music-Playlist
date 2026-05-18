package music_command;
/* Прекратява изпълнението на програмата, като гарантира, че всички сесии са затворени. */
public class ExitCommand implements Command {
    @Override
    public String execute(String[] args) {
        return "Exiting the program...";
    }
}
