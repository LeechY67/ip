package seedu.blondeblazer.command;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.storage.Storage;
import seedu.blondeblazer.task.Note;
import seedu.blondeblazer.task.TaskList;

/**
 * Represents a command that adds a note to the task list.
 * <p>
 * The input for this command should include the note text after the {@code note} keyword.
 * The created note will be added to the task list and saved to storage.
 * </p >
 */
public class NoteCommand extends Command {
    private static final String COMMAND_WORD = "note";
    private final String noteText;

    public NoteCommand(String input) throws BlondeBlazerException {
        String trimmed = input == null ? "" : input.trim();

        if (trimmed.equals(COMMAND_WORD)) {
            throw new BlondeBlazerException("Note text cannot be empty. Usage: note <text>");
        }

        this.noteText = trimmed.substring(COMMAND_WORD.length()).trim();
        if (noteText.isEmpty()) {
            throw new BlondeBlazerException("Note text cannot be empty. Usage: note <text>");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BlondeBlazerException {
        Note note = new Note(noteText);
        tasks.add(note);
        storage.save(tasks.getTasks());

        return  "Got it, I've added this note:\n"
                + note + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}