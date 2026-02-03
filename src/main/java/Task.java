public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

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

    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    protected abstract String getType();

    @Override
    public String toString() {
        return getType() + getStatus() + " " + taskName;
    }
}