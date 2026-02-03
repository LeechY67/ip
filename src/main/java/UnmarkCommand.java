public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 7) {
            throw new BlondeBlazerException("Bro, I don't know what you're gonna unmark...");
        }
        this.index = parseIndex(input.substring(7));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BlondeBlazerException {
        tasks.unmark(index);
        storage.save(tasks.getTasks());
        ui.showUnmarked(tasks.get(index), index + 1);
    }

    private int parseIndex(String s) throws BlondeBlazerException {
        try {
            return Integer.parseInt(s.trim()) - 1;
        } catch (Exception e) {
            throw new BlondeBlazerException("Please provide a valid number.");
        }
    }
}