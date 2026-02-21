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
import seedu.blondeblazer.command.NoteCommand;
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
        // AI-assisted: Refactored to parse the command word once (indexOf + substring),
        // avoiding multiple startsWith checks and improving readability/efficiency.
        // Also, fixing the bug of recording wrong input when user types command without 'SPACE'
        String input = fullCommand.trim();
        if (input.isEmpty()) {
            throw new BlondeBlazerException("Come on, I don't even know what does this  mean!");
        }

        int firstSpace = input.indexOf(' ');
        String commandWord = (firstSpace == -1) ? input : input.substring(0, firstSpace);
        boolean hasArgs = firstSpace != -1 && !input.substring(firstSpace + 1).trim().isEmpty();

        switch (commandWord) {
        case "bye":
            if (hasArgs) {
                break;
            }
            return new ByeCommand();
        case "list":
            if (hasArgs) {
                break;
            }
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
        case "note":
            return new NoteCommand(input);
        default:
            break;
        }

        throw new BlondeBlazerException("Come on, I don't even know what does this " + input + " mean!");
    }
}