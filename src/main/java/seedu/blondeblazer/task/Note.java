package seedu.blondeblazer.task;

/**
 * A Note is a lightweight task-like item used for Category D extension.
 * It behaves like a normal Task so that existing list/delete/find/storage flows can be reused.
 */
public class Note extends Task {

    public Note(String noteText) {
        super(noteText);
    }

    @Override
    public String getType() { return "[N]"; }
}