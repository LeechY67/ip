package seedu.blondeblazer;

import java.util.ArrayList;

import seedu.blondeblazer.command.Command;
import seedu.blondeblazer.parser.Parser;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
import seedu.blondeblazer.ui.Ui;

public class BlondeBlazer {
    private static final String DEFAULT_FILE_PATH = "data/seedu.blondeblazer.BlondeBlazer.txt";

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public BlondeBlazer(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        TaskList loaded;
        try {
            ArrayList<Task> loadedTasks = storage.load();
            loaded = new TaskList(loadedTasks);
        } catch (BlondeBlazerException e) {
            ui.showLoadingError();
            loaded = new TaskList();
        }
        tasks = loaded;
    }

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BlondeBlazerException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError(e.getMessage() == null ? "Something went wrong." : e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new BlondeBlazer(DEFAULT_FILE_PATH).run();
    }
}