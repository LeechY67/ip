package seedu.blondeblazer.task;

/**
 * Represents a task with a starting time and ending time.
 * A {@code Event} task has a description and a starting and ending time.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs a {@code Event} task with given description
     * and starting and ending time.
     *
     * @param taskName Description of the task.
     * @param from Starting time.
     * @param to Ending time.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    protected String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getType() + getStatus() + " " + taskName + " (from: " + from + " to: " + to + ")";
    }
}
