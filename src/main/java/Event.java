public class Event extends Task {
    private final String from;
    private final String to;

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
