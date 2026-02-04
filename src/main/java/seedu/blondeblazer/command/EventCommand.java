package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Event;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.ui.Ui;

public class EventCommand extends Command {
    private final String desc;
    private final String from;
    private final String to;

    public EventCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 6) {
            throw new BlondeBlazerException(
                    "Wait, you can't just say event without a clear stuff! State what you're gonna do after event.");
        }

        String rest = input.substring(6).trim();
        String[] p1 = rest.split(" /from ", 2);
        if (p1.length < 2) {
            throw new BlondeBlazerException(
                    "Wait, you can't just say event without a clear stuff! State what you're gonna do after event.");
        }

        String d = p1[0].trim();
        String[] p2 = p1[1].split(" /to ", 2);
        if (d.isEmpty() || p2.length < 2 || p2[0].trim().isEmpty() || p2[1].trim().isEmpty()) {
            throw new BlondeBlazerException(
                    "Wait, you can't just say event without a clear stuff! State what you're gonna do after event.");
        }

        desc = d;
        from = p2[0].trim();
        to = p2[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BlondeBlazerException {
        Task t = new Event(desc, from, to);
        tasks.add(t);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(t, tasks.size());
    }
}