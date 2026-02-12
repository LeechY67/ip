package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String input) throws BlondeBlazerException {
        if (input.length() <= 5) {
            throw new BlondeBlazerException("blondeblazer.command.Command 'find' should be followed with a keyword.");
        }

        this.keyword = input.substring(5).trim();
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTasks().get(i);

            if (task.getTaskName().contains(keyword)) {
                sb.append(i + 1).append(". ").append(task).append("\n");
                found = true;
            }
        }

        if (!found) {
            return "No related findings...";
        }

        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}

