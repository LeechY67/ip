package seedu.blondeblazer.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.command.Command;
import seedu.blondeblazer.command.DeadlineCommand;
import seedu.blondeblazer.command.ToDoCommand;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void parserGiveValidCommandTest() throws BlondeBlazerException {
        // AI-assisted: Added coverage for multiple command types to ensure parser refactor
        // preserves behavior while improving efficiency.
        Command cmd = parser.parse("todo go to the gym");
        assertInstanceOf(ToDoCommand.class, cmd);

        Command deadlineCmd = parser.parse("deadline return book /by 2pm");
        assertInstanceOf(DeadlineCommand.class, deadlineCmd);
    }

    @Test
    public void parserHandleError() {
        assertThrows(
                BlondeBlazerException.class,
                () -> parser.parse("qwerty")
        );
    }

    @Test
    public void parserDeadlineMissingBy_throws() {
        assertThrows(
                BlondeBlazerException.class,
                () -> parser.parse("deadline return book")
        );
    }
}