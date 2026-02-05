package seedu.blondeblazer.parser;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.command.*;

/**
 * Parses user input and returns the corresponding {@link Command}.
 *
 * <p>The {@code Parser} is responsible for interpreting the user's input string and
 * determining which {@code Command} should be executed.</p>
 */
public class Parser {
    /**
     * Parses the full user input and returns the proper {@code Command}.
     *
     * @param fullCommand Input entered by the user.
     * @return A {@code Command} corresponding to the user's input.
     * @throws BlondeBlazerException If the input does not match with any command.
     */
    public static Command parse(String fullCommand) throws BlondeBlazerException {
        String input = fullCommand == null ? "" : fullCommand.trim();

        if (input.equals("bye")) {
            return new ByeCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
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
