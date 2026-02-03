public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    protected String getType() {
        return "[T]";
    }
}