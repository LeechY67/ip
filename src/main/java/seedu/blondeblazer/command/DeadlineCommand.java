package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Deadline;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;

public class DeadlineCommand extends Command {
    private static final String ERROR_MESSAGE =
            "Wait, you can't just say deadline without a clear stuff! " +
                    "State what you're gonna do after deadline.";

    private final String desc;
    private final String by;

    public DeadlineCommand(String input) throws BlondeBlazerException {
        // AI-assisted: Replaced split(" /by ") with index-based parsing to reduce
        // intermediate objects and handle variable whitespace more robustly.
        if (input == null) {
            throw new BlondeBlazerException(ERROR_MESSAGE);
        }

        String trimmed = input.trim();
        if (trimmed.length() <= 9) {
            throw new BlondeBlazerException(ERROR_MESSAGE);
        }

        // Keep the same contract as before: the constructor receives the full input starting with "deadline"
        String rest = trimmed.substring(8).trim();
        if (rest.isEmpty()) {
            throw new BlondeBlazerException(ERROR_MESSAGE);
        }

        int byIndex = rest.indexOf("/by");
        if (byIndex == -1) {
            throw new BlondeBlazerException(ERROR_MESSAGE);
        }

        String descPart = rest.substring(0, byIndex).trim();
        String byPart = rest.substring(byIndex + 3).trim();

        if (descPart.isEmpty() || byPart.isEmpty()) {
            throw new BlondeBlazerException(ERROR_MESSAGE);
        }

        this.desc = descPart;
        this.by = byPart;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BlondeBlazerException {
        Task t = new Deadline(desc, by);
        tasks.add(t);
        storage.save(tasks.getTasks());

        return "Got it, I've added this task:\n"
                + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}