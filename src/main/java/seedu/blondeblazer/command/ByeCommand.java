package seedu.blondeblazer.command;

import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
