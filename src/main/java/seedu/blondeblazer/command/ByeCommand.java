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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
