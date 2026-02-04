package seedu.blondeblazer.parser;

import org.junit.jupiter.api.Test;
import seedu.blondeblazer.BlondeBlazerException;
import seedu.blondeblazer.command.Command;
import seedu.blondeblazer.command.ToDoCommand;

import static org.junit.jupiter.api.Assertions.*;

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
