package seedu.blondeblazer.command;

import java.time.LocalDate;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Deadline;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;

public class OnCommand extends Command {
    private final LocalDate targetDate;

    public OnCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 3) {
            throw new BlondeBlazerException("blondeblazer.command.Command 'on' should be followed with a date.");
        }

        String date = input.substring(3).trim();
        try {
            targetDate = LocalDate.parse(date);
        } catch (Exception e) {
            throw new BlondeBlazerException("Invalid date format!");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks on ")
                .append(targetDate)
                .append(":\n");

        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTasks().get(i);

            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getBy().equals(targetDate)) {
                    sb.append(i + 1)
                            .append(". ")
                            .append(d)
                            .append("\n");
                    found = true;
                }
            }
        }

        if (!found) {
            return "No tasks found on that date.";
        }

        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}