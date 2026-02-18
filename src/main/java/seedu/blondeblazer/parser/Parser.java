package seedu.blondeblazer.parser;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.command.ByeCommand;
import seedu.blondeblazer.command.Command;
import seedu.blondeblazer.command.DeadlineCommand;
import seedu.blondeblazer.command.DeleteCommand;
import seedu.blondeblazer.command.EventCommand;
import seedu.blondeblazer.command.FindCommand;
import seedu.blondeblazer.command.ListCommand;
import seedu.blondeblazer.command.MarkCommand;
import seedu.blondeblazer.command.OnCommand;
import seedu.blondeblazer.command.ToDoCommand;
import seedu.blondeblazer.command.UnmarkCommand;

/**
 * Parses user input and returns the corresponding {@link Command}.
 *
 * <p>The {@code Parser} is responsible for interpreting the user's input string and
 * determining which {@code Command} should be executed.</p >
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
        assert fullCommand != null : "Input command should not be null";

        String input = fullCommand.trim();
        if (input.isEmpty()) {
            throw new BlondeBlazerException("Empty command.");
        }

        String[] parts = input.split("\\s+", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "find":
            return new FindCommand(input);

        case "mark":
            return new MarkCommand(input);

        case "unmark":
            return new UnmarkCommand(input);

        case "todo":
            return new ToDoCommand(input);

        case "deadline":
            return new DeadlineCommand(input);

        case "event":
            return new EventCommand(input);

        case "delete":
            return new DeleteCommand(input);

        case "on":
            return new OnCommand(input);

        default:
            throw new BlondeBlazerException("Come on, I don't even know what does this " + commandWord + " mean!");
        }
    }
}