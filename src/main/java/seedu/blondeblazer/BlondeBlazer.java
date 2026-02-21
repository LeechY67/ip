package seedu.blondeblazer;

import java.util.ArrayList;

import seedu.blondeblazer.command.Command;
import seedu.blondeblazer.parser.Parser;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.gui.TextMessage;

public class BlondeBlazer {
    private static final String DEFAULT_FILE_PATH = "data/seedu.blondeblazer.BlondeBlazer.txt";

    private final TextMessage tm;
    private final Storage storage;
    private final TaskList tasks;

    public BlondeBlazer(String filePath) {
        tm = new TextMessage();
        storage = new Storage(filePath);

        TaskList loaded;
        try {
            ArrayList<Task> loadedTasks = storage.load();
            loaded = new TaskList(loadedTasks);
        } catch (BlondeBlazerException e) {
            tm.showLoadingError();
            loaded = new TaskList();
        }
        tasks = loaded;
    }

    private boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String result = c.execute(tasks, storage);
            isExit = c.isExit();
            return result;
        } catch (BlondeBlazerException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage() == null
                    ? "Something went wrong."
                    : e.getMessage();
        }
    }
}