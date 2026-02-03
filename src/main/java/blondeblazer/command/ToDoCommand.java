package blondeblazer.command;

import blondeblazer.BlondeBlazerException;
import blondeblazer.storage.Storage;
import blondeblazer.task.Task;
import blondeblazer.task.TaskList;
import blondeblazer.task.ToDo;
import blondeblazer.ui.Ui;

public class ToDoCommand extends Command {
    private final String description;

    public ToDoCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 5) {
            throw new BlondeBlazerException(
                    "Wait, you can't just say todo without a clear stuff! State what you're gonna do after todo.");
        }
        description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new BlondeBlazerException(
                    "Wait, you can't just say todo without a clear stuff! State what you're gonna do after todo.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BlondeBlazerException {
        Task t = new ToDo(description);
        tasks.add(t);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(t, tasks.size());
    }
}