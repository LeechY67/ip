package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 7) {
            throw new BlondeBlazerException("Delete what? At least give me a number.");
        }
        this.index = parseIndex(input.substring(7));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BlondeBlazerException {
        Task removed = tasks.remove(index);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(removed, tasks.size());
    }

    private int parseIndex(String s) throws BlondeBlazerException {
        try {
            return Integer.parseInt(s.trim()) - 1;
        } catch (Exception e) {
            throw new BlondeBlazerException("Please provide a valid number.");
        }
    }
}