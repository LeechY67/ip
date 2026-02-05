package seedu.blondeblazer.task;

/**
 * Represents a task in the task list.
 * A {@code Task} has a name and a completion status.
 * Subclasses define the specific types of a task.
 */
public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructs a task with a given name.
     *
     * @param taskName The description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Visualizes the status of completion.
     *
     * @return A string that shows the completion status.
     */
    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    protected abstract String getType();

    @Override
    public String toString() {
        return getType() + getStatus() + " " + taskName;
    }
}