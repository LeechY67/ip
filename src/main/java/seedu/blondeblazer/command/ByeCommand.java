package seedu.blondeblazer.command;

import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.ui.Ui;

/**
 * Represents a command that ends the program.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        isExit = true;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
