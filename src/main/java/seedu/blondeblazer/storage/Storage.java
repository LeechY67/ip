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
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.ToDo;

public class Storage {
    private static final String SPLIT_REGEX = "\\s*\\|\\s*";
    private static final String DONE_TRUE = "1";

    private static final String TYPE_TODO = "T";
    private static final String TYPE_DEADLINE = "D";
    private static final String TYPE_EVENT = "E";

    private static final int MIN_PARTS_BASIC = 3;
    private static final int MIN_PARTS_DEADLINE = 4;
    private static final int MIN_PARTS_EVENT = 5;

    private final Path dataPath;

    public Storage(String filePath) {
        this.dataPath = Paths.get(filePath);
    }

    public ArrayList<Task> load() throws BlondeBlazerException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!Files.exists(dataPath)) {
                return tasks;
            }

            List<String> lines = Files.readAllLines(dataPath);
            for (String line : lines) {
                addTaskIfValidLine(tasks, line);
            }
            return tasks;

        } catch (IOException e) {
            throw new BlondeBlazerException("Failed to load tasks: " + e.getMessage());
        }
    }

    private void addTaskIfValidLine(ArrayList<Task> tasks, String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        try {
            Task task = decodeTask(line);
            if (task != null) {
                tasks.add(task);
            }
        } catch (BlondeBlazerException e) {
            // 保持你原本“坏行跳过”的行为（只是更清晰）
            System.out.println("Skipping corrupted line: " + line);
        }
    }

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
            return TYPE_TODO + " | " + done + " | " + t.getTaskName();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return TYPE_DEADLINE + " | " + done + " | " + d.getTaskName() + " | " + d.getByRaw();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return TYPE_EVENT + " | " + done + " | " + e.getTaskName() + " | " + e.getFrom() + " | " + e.getTo();
        } else {
            // 如果你未来又加了类型，这里至少不会炸掉
            return TYPE_TODO + " | " + done + " | " + t.getTaskName();
        }
    }

    private Task decodeTask(String line) throws BlondeBlazerException {
        String[] parts = line.split(SPLIT_REGEX);
        if (parts.length < MIN_PARTS_BASIC) {
            throw new BlondeBlazerException("Corrupted data line: " + line);
        }

        String type = parts[0].trim();
        boolean done = parts[1].trim().equals(DONE_TRUE);
        String desc = parts[2];

        Task t;
        switch (type) {
        case TYPE_TODO:
            t = new ToDo(desc);
            break;

        case TYPE_DEADLINE:
            if (parts.length < MIN_PARTS_DEADLINE) {
                throw new BlondeBlazerException("Corrupted deadline line: " + line);
            }
            t = new Deadline(desc, parts[3]);
            break;

        case TYPE_EVENT:
            if (parts.length < MIN_PARTS_EVENT) {
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