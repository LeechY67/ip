package seedu.blondeblazer.storage;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.task.Deadline;
import seedu.blondeblazer.task.Event;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving of tasks to a local data file.
 *
 * <p>The Tasks are stored in a plain text file and encoded line by line.</p>
 */
public class Storage {
    private final Path dataPath;

    /**
     * Creates a storage that reads from and write to the given file path.
     *
     * @param filePath Path to the local data file.
     */
    public Storage(String filePath) {
        this.dataPath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the data file.
     *
     * @return A list of tasks from local file.
     * @throws BlondeBlazerException If an error occurs when reading the file.
     */
    public ArrayList<Task> load() throws BlondeBlazerException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!Files.exists(dataPath)) {
                return tasks;
            }

            List<String> lines = Files.readAllLines(dataPath);
            for (String line : lines) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                try {
                    Task t = decodeTask(line);
                    if (t != null) {
                        tasks.add(t);
                    }
                } catch (Exception ex) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new BlondeBlazerException("Failed to load tasks: " + e.getMessage());
        }
    }

    /**
     * Saves the given list of tasks into the local file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws BlondeBlazerException If an error occurs when writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws BlondeBlazerException {
        try {
            Path parent = dataPath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                lines.add(encodeTask(t));
            }
            Files.write(dataPath, lines);
        } catch (IOException e) {
            throw new BlondeBlazerException("Failed to save tasks: " + e.getMessage());
        }
    }

    private String encodeTask(Task t) {
        String done = t.isDone() ? "1" : "0";

        if (t instanceof ToDo) {
            return "T | " + done + " | " + t.getTaskName();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + done + " | " + d.getTaskName() + " | " + d.getByRaw();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return "E | " + done + " | " + e.getTaskName() + " | " + e.getFrom() + " | " + e.getTo();
        } else {
            return "T | " + done + " | " + t.getTaskName();
        }
    }

    private Task decodeTask(String line) throws BlondeBlazerException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new BlondeBlazerException("Corrupted data line: " + line);
        }

        String type = parts[0].trim();
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2];

        Task t;
        switch (type) {
            case "T":
                t = new ToDo(desc);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new BlondeBlazerException("Corrupted deadline line: " + line);
                }
                t = new Deadline(desc, parts[3]);
                break;
            case "E":
                if (parts.length < 5) {
                    throw new BlondeBlazerException("Corrupted event line: " + line);
                }
                t = new Event(desc, parts[3], parts[4]);
                break;
            default:
                throw new BlondeBlazerException("Unknown task type: " + type);
        }

        if (done) {
            t.mark();
        }
        return t;
    }
}
