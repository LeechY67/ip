package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
/**
 * Represents a command that marks a task as not done.
 * <p>
 * The input for this command should specify the task index (1-based) to be unmarked.
 * The task list will be updated and the changes will be saved to storage.
 * </p >
 */
public class UnmarkCommand extends Command {
    private final int index;
    /**
     * Constructs an {@code UnmarkCommand} with the user input.
     *
     * @param input Full user input.
     * @throws BlondeBlazerException If the task index is missing or invalid.
     */
    public UnmarkCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 7) {
            throw new BlondeBlazerException("Bro, I don't know what you're gonna unmark...");
        }
        this.index = parseIndex(input.substring(7));
    }

    /**
     * Executes the command to unmark a task.
     *
     * @param tasks Current task list.
     * @param storage Storage component to persist changes.
     * @return A user-facing message confirming the task has been unmarked.
     * @throws BlondeBlazerException If the task index is out of range.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BlondeBlazerException {
        tasks.unmark(index);
        Task t = tasks.getTasks().get(index);
        storage.save(tasks.getTasks());

        return "OK, I've marked this task as not done.\n"
                + (index + 1) + ". " + t;
    }

    private int parseIndex(String s) throws BlondeBlazerException {
        try {
            return Integer.parseInt(s.trim()) - 1;
        } catch (Exception e) {
            throw new BlondeBlazerException("Please provide a valid number.");
        }
    }
}