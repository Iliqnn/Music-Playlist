public class ExitCommand implements Command {
    @Override
    public String execute(String[] args) {
        System.out.println("Exiting the program...");
        System.exit(0);
        return "";
    }
}
