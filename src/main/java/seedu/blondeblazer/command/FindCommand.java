package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.ui.Ui;


public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 5) {
            throw new BlondeBlazerException("blondeblazer.command.Command 'find' should be followed with a keyword.");
        }

        this.keyword = input.substring(5).trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindResult();
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTasks().get(i);
            if (task.getTaskName().contains(keyword)) {
                System.out.println((i + 1) + ". " + task);
                found = true;
            }
        }

        if (!found) {
            ui.showNoFinding();
        }
    }
}
