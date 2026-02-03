import java.time.LocalDate;

public class OnCommand extends Command {
    private final LocalDate targetDate;

    public OnCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 3) {
            throw new BlondeBlazerException("Command 'on' should be followed with a date.");
        }

        String date = input.substring(3).trim();
        try {
            targetDate = LocalDate.parse(date);
        } catch (Exception e) {
            throw new BlondeBlazerException("Invalid date format!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasksOnDateHeader(targetDate.toString());

        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTasks().get(i);
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getBy().equals(targetDate)) {
                    System.out.println((i + 1) + ". " + d);
                    found = true;
                }
            }
        }

        if (!found) {
            ui.showNoTasksFoundOnDate();
        }
    }
}