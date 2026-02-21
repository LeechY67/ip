package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Task;
import seedu.blondeblazer.task.TaskList;
/**
 * Represents a command that finds and lists tasks whose names contain a given keyword.
 * <p>
 * The input for this command should include a keyword to search for.
 * Tasks with task names containing the keyword will be listed in their original order.
 * </p >
 */
public class FindCommand extends Command {
    private final String keyword;
    /**
     * Constructs a {@code FindCommand} with the user input.
     *
     * @param input Full user input.
     * @throws BlondeBlazerException If the keyword is missing.
     */
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

    /**
     * Executes the command to find tasks matching the keyword.
     *
     * @param tasks Current task list.
     * @param storage Storage component (not used by this command).
     * @return A user-facing message listing matching tasks, if any.
     */
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