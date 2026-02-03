import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks == null ? new ArrayList<>() : tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task get(int index) throws BlondeBlazerException {
        if (index < 0 || index >= tasks.size()) {
            throw new BlondeBlazerException("Index out of range.");
        }
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) throws BlondeBlazerException {
        if (index < 0 || index >= tasks.size()) {
            throw new BlondeBlazerException("Index out of range.");
        }
        return tasks.remove(index);
    }

    public void mark(int index) throws BlondeBlazerException {
        get(index).mark();
    }

    public void unmark(int index) throws BlondeBlazerException {
        get(index).unmark();
    }
}