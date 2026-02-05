package seedu.blondeblazer.task;

/**
 * Represents a task with a name but no any other descriptions.
 */
public class ToDo extends Task {
    /**
     * Constructs a task with a given description.
     *
     * @param taskName name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    protected String getType() {
        return "[T]";
    }
}