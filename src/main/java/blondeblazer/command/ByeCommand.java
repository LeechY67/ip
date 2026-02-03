package blondeblazer.command;

import blondeblazer.storage.Storage;
import blondeblazer.task.TaskList;
import blondeblazer.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
