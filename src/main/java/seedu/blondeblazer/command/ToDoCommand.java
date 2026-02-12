package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.task.ToDo;

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
    public String execute(TaskList tasks, Storage storage) throws BlondeBlazerException {
        Task t = new ToDo(description);
        tasks.add(t);
        storage.save(tasks.getTasks());

        return  "Got it, I've added this task:\n"
                + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}