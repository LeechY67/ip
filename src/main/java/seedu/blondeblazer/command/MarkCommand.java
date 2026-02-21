package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
/**
 * Represents a command that marks a task as done.
 * <p>
 * The input for this command should specify the task index (1-based) to be marked.
 * The task list will be updated and the changes will be saved to storage.
 * </p >
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 5) {
            throw new BlondeBlazerException("Bro, I don't know what you're gonna mark...");
        }
        this.index = parseIndex(input.substring(5));
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BlondeBlazerException {
        tasks.mark(index);
        Task t = tasks.getTasks().get(index);
        storage.save(tasks.getTasks());

        return "Nice, I've marked this task as done!\n"
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