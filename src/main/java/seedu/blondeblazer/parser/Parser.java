package seedu.blondeblazer.parser;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.command.*;

public class Parser {
    public static Command parse(String fullCommand) throws BlondeBlazerException {
        String input = fullCommand == null ? "" : fullCommand.trim();

        if (input.equals("bye")) {
            return new ByeCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        if (input.startsWith("find")) {
            return new FindCommand(input);
        }
        if (input.startsWith("mark")) {
            return new MarkCommand(input);
        }
        if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        }
        if (input.startsWith("todo")) {
            return new ToDoCommand(input);
        }
        if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        }
        if (input.startsWith("event")) {
            return new EventCommand(input);
        }
        if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        }
        if (input.startsWith("on")) {
            return new OnCommand(input);
        }

        throw new BlondeBlazerException("Come on, I don't even know what does this mean!");
    }
}
