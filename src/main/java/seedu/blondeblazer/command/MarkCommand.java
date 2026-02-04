package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 5) {
            throw new BlondeBlazerException("Bro, I don't know what you're gonna mark...");
        }
        this.index = parseIndex(input.substring(5));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BlondeBlazerException {
        tasks.mark(index);
        storage.save(tasks.getTasks());
        ui.showMarked(tasks.get(index), index + 1);
    }

    private int parseIndex(String s) throws BlondeBlazerException {
        try {
            return Integer.parseInt(s.trim()) - 1;
        } catch (Exception e) {
            throw new BlondeBlazerException("Please provide a valid number.");
        }
    }
}