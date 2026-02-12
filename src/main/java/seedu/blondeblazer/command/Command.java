package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.ui.Ui;

/**
 * Represents a command to be executed by the application.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Returns whether this command is leading to the exit of the program.
     *
     * @return {@code true} if the application should exit after this execution.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Execute this command.
     *
     * @param tasks The task list to operate on.
     * @param storage The storage for loading and saving.
     * @throws BlondeBlazerException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws BlondeBlazerException;
}