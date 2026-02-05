package seedu.blondeblazer.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.command.Command;
import seedu.blondeblazer.command.ToDoCommand;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void parserGiveValidCommandTest() throws BlondeBlazerException {
        Command cmd = parser.parse("todo go to the gym");
        assertInstanceOf(ToDoCommand.class, cmd);
    }

    @Test
    public void parserHandleError(){
        assertThrows(
                BlondeBlazerException.class,
                () -> parser.parse("qwerty")
        );
    }
}
