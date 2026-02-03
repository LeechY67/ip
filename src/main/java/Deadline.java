import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate by;

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
