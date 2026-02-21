package seedu.blondeblazer.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.blondeblazer.BlondeBlazerException;

public class DeadlineCommandTest {

    @Test
    public void constructor_validInput_doesNotThrow() {
        assertDoesNotThrow(() -> new DeadlineCommand("deadline return book /by 2pm"));
    }

    @Test
    public void constructor_missingBy_throwsException() {
        assertThrows(BlondeBlazerException.class, () -> new DeadlineCommand("deadline return book"));
    }

    @Test
    public void constructor_emptyDescription_throwsException() {
        assertThrows(BlondeBlazerException.class, () -> new DeadlineCommand("deadline /by 2pm"));
    }

    @Test
    public void constructor_emptyBy_throwsException() {
        assertThrows(BlondeBlazerException.class, () -> new DeadlineCommand("deadline return book /by "));
    }
}