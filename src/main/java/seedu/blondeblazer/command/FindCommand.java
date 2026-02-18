package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String input) throws BlondeBlazerException {
        if (input == null) {
            throw new BlondeBlazerException("Find keyword cannot be null.");
        }

        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new BlondeBlazerException("Please provide a keyword to find.");
        }

        String[] parts = trimmed.split("\\s+", 2);
        if (parts.length == 1) {
            if ("find".equals(parts[0])) {
                throw new BlondeBlazerException("Please provide a keyword to find.");
            }
            this.keyword = parts[0];
            return;
        }

        if ("find".equals(parts[0])) {
            String k = parts[1].trim();
            if (k.isEmpty()) {
                throw new BlondeBlazerException("Please provide a keyword to find.");
            }
            this.keyword = k;
        } else {
            this.keyword = parts[1].trim().isEmpty() ? parts[0] : trimmed;
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        boolean hasMatches = false;
        int displayIndex = 1;

        for (Task task : tasks.getTasks()) {
            if (task.getTaskName().contains(keyword)) {
                if (hasMatches) {
                    sb.append("\n");
                }
                sb.append(displayIndex).append(". ").append(task);
                hasMatches = true;
            }
            displayIndex++;
        }

        if (!hasMatches) {
            return "No related findings...";
        }
        return sb.toString();
    }
}