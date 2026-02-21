package seedu.blondeblazer.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.task.Deadline;
import seedu.blondeblazer.task.Event;
import seedu.blondeblazer.task.Note;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.ToDo;

/**
 * Handles loading and saving of tasks to persistent storage.
 *
 * <p>Tasks are serialized into a simple delimited text format and written to disk,
 * and reconstructed back into {@link Task} objects when loading.</p >
 */
public class Storage {
    private static final String DELIMITER_REGEX = "\\s*\\|\\|\\s*";
    private static final String DONE_TRUE = "1";
    private static final int MIN_PARTS_FOR_TASK = 3;

    private final Path dataPath;

    /**
     * Constructs a {@code Storage} instance with the given file path.
     *
     * @param filePath Path to the data file used for persistence.
     */
    public Storage(String filePath) {
        this.dataPath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the data file.
     *
     * @return An {@link ArrayList} of reconstructed tasks. Returns an empty list if the file does not exist.
     * @throws BlondeBlazerException If an I/O error occurs while reading the file.
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
                    Task task = decodeTask(line);
                    if (task != null) {
                        tasks.add(task);
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
     * Saves the given tasks to the data file.
     *
     * @param tasks List of tasks to persist.
     * @throws BlondeBlazerException If an I/O error occurs while writing the file.
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

    /**
     * Encodes a {@link Task} into its storage string representation.
     *
     * @param t Task to encode.
     * @return Encoded string representation of the task.
     */
    private String encodeTask(Task t) {
        String done = t.isDone() ? DONE_TRUE : "0";

        if (t instanceof ToDo) {
            return "T||" + done + "||" + t.getTaskName();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D||" + done + "||" + d.getTaskName() + "||" + d.getByRaw();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return "E||" + done + "||" + e.getTaskName() + "||" + e.getFrom() + "||" + e.getTo();
        } else if (t instanceof Note) {
            return "N||" + done + "||" + t.getTaskName();
        } else {
            return "T||" + done + "||" + t.getTaskName();
        }
    }

    /**
     * Decodes a line from storage into a {@link Task}.
     *
     * @param line Raw line from the data file.
     * @return Reconstructed task.
     * @throws BlondeBlazerException If the line is malformed or contains unknown task type.
     */
    private Task decodeTask(String line) throws BlondeBlazerException {
        String[] parts = line.split(DELIMITER_REGEX);
        if (parts.length < MIN_PARTS_FOR_TASK) {
            throw new BlondeBlazerException("Corrupted data line: " + line);
        }

        String type = parts[0].trim();
        boolean done = parts[1].trim().equals(DONE_TRUE);
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
        case "N":
            t = new Note(desc);
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