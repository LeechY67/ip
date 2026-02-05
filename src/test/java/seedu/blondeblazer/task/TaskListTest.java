package seedu.blondeblazer.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTaskIncreasesSize() {
        TaskList tl = new TaskList();
        int before = tl.size();

        tl.add(new ToDo("read book"));
        assertEquals(before + 1, tl.size());
    }

    @Test
    public void deleteValidIndexRemovesTask() throws Exception {
        TaskList tl = new TaskList();
        tl.add(new ToDo("aaa"));
        tl.add(new ToDo("bbb"));

        Task removed = tl.remove(1);
        assertEquals("[T][ ] bbb", removed.toString());
        assertEquals(1, tl.size());
    }
}
