package seedu.blondeblazer.task;

import seedu.blondeblazer.BlondeBlazerException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} task has a description and a due date.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructs a {@code Deadline} task with the given description and due date.
     *
     * @param taskName Description of the task.
     * @param by Due date of the task.
     * @throws BlondeBlazerException If the data format is invalid.
     */
    public Deadline(String taskName, String by) throws BlondeBlazerException {
        super(taskName);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (Exception e) {
            throw new BlondeBlazerException("Invalid Date format! Use yyyy-mm-dd!");
        }
    }

    public LocalDate getBy() {
        return by;
    }

    public String getByRaw() {
        return by.toString();
    }

    @Override
    protected String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getType() + getStatus() + " " + taskName
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
