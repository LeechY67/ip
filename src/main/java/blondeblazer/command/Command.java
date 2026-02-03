package blondeblazer.command;

import blondeblazer.BlondeBlazerException;
import blondeblazer.storage.Storage;
import blondeblazer.task.TaskList;
import blondeblazer.ui.Ui;

public abstract class Command {
    protected boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BlondeBlazerException;
}