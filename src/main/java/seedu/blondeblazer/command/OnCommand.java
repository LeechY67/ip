package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Deadline;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.ui.Ui;

import java.time.LocalDate;

/**
 * Represents a command that lists all {@link Deadline} tasks that have the specific due date.
 *
 * <p>The command takes a date as the input and returns all deadlines that are on that date.</p>
 */
public class OnCommand extends Command {
    private final LocalDate targetDate;

    /**
     * Constructs an {@code OnCommand} using user input.
     *
     * @param input The full user input.
     * @throws BlondeBlazerException If input is invalid.
     */
    public OnCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 3) {
            throw new BlondeBlazerException("blondeblazer.command.Command 'on' should be followed with a date.");
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