package blondeblazer.command;

import blondeblazer.storage.Storage;
import blondeblazer.task.TaskList;
import blondeblazer.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showEmptyList();
            return;
        }

        ui.showListHeader();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTasks().get(i));
        }
    }
}