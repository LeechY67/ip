package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Deadline;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.ui.Ui;

public class DeadlineCommand extends Command {
    private final String desc;
    private final String by;

    public DeadlineCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 9) {
            throw new BlondeBlazerException(
                    "Wait, you can't just say deadline without a clear stuff! " +
                            "State what you're gonna do after deadline.");
        }

        String rest = input.substring(9).trim();
        String[] parts = rest.split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BlondeBlazerException(
                    "Wait, you can't just say deadline without a clear stuff! " +
                            "State what you're gonna do after deadline.");
        }

        desc = parts[0].trim();
        by = parts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BlondeBlazerException {
        Task t = new Deadline(desc, by);
        tasks.add(t);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(t, tasks.size());
    }
}