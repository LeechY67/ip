import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BlondeBlazer {
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "BlondeBlazer.txt";
    private static final Path DATA_PATH = Paths.get(DATA_DIR, DATA_FILE);

    private static void saveTasks(ArrayList<Task> tasks) throws BlondeBlazerException {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));

            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                lines.add(encodeTask(t));
            }
            Files.write(DATA_PATH, lines);
        } catch (IOException e) {
            throw new BlondeBlazerException("Failed to save tasks: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasks() throws BlondeBlazerException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!Files.exists(DATA_PATH)) {
                return tasks;
            }

            List<String> lines = Files.readAllLines(DATA_PATH);
            for (String line : lines) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                try {
                    Task t = decodeTask(line);
                    if (t != null) {
                        tasks.add(t);
                    }
                } catch (Exception ex) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new BlondeBlazerException("Failed to load tasks: " + e.getMessage());
        }
    }

    private static String encodeTask(Task t) {
        String done = t.isDone ? "1" : "0";

        if (t instanceof ToDo) {
            return "T | " + done + " | " + t.taskName;
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + done + " | " + d.taskName + " | " + d.by;
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return "E | " + done + " | " + e.taskName + " | " + e.from + " | " + e.to;
        } else {
            return "T | " + done + " | " + t.taskName;
        }
    }

    private static Task decodeTask(String line) throws BlondeBlazerException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new BlondeBlazerException("Corrupted data line: " + line);
        }

        String type = parts[0].trim();
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2];

        Task t;
        switch (type) {
            case "T":
                t = new ToDo(desc);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new BlondeBlazerException("Corrupted deadline line: " + line);
                }
                t = new Deadline(desc, parts[3]);
                break;
            case "E":
                if (parts.length < 5) {
                    throw new BlondeBlazerException("Corrupted event line: " + line);
                }
                t = new Event(desc, parts[3], parts[4]);
                break;
            default:
                throw new BlondeBlazerException("Unknown task type: " + type);
        }

        if (done) {
            t.mark();
        }
        return t;
    }

    public static void main(String[] args) {
        String logo = "----------------------------------------------------------------------------------------";
        System.out.println(logo + "\n" + "Hello! I'm BlondeBlazer!");
        System.out.println("What can I do for you?");
        System.out.println(logo);

        ArrayList<Task> tasks = null;
        try {
            tasks = loadTasks();
        } catch (BlondeBlazerException e) {
            throw new RuntimeException(e);
        }

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            try {
                if (line.equals("bye")) {
                    System.out.println(logo);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(logo);
                    break;
                } else if (line.equals("list")) {
                    if (tasks.isEmpty()) {
                        System.out.println("Well, I got nothing to list out. Try add something.");
                    } else {
                        System.out.println("Here are the tasks in your list: ");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i).toString());
                        }
                    }
                } else if (line.startsWith("mark")) {
                    if (line.length() <= 5) {
                        throw new BlondeBlazerException("Bro, I don't know what you're gonna mark...");
                    }

                    int index = Integer.parseInt(line.substring(5)) - 1;

                    tasks.get(index).mark();
                    saveTasks(tasks);
                    System.out.println("Nice, I've marked this task as done!");
                    System.out.println((index + 1) + ". " + tasks.get(index).toString());

                } else if (line.startsWith("unmark")) {
                    if (line.length() <= 7) {
                        throw new BlondeBlazerException("Bro, I don't know what you're gonna unmark...");
                    }

                    int index = Integer.parseInt(line.substring(7)) - 1;
                    tasks.get(index).unmark();
                    saveTasks(tasks);
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println((index + 1) + ". " + tasks.get(index).toString());

                } else if (line.startsWith("todo")) {
                    if (line.length() <= 5) {
                        throw new BlondeBlazerException("Wait, you can't just say todo without a clear stuff! State what you're gonna do after todo.");
                    }

                    String dex = line.substring(5);

                    if (dex.isEmpty()) {
                        throw new BlondeBlazerException("Wait, you can't just say todo without a clear stuff! State what you're gonna do after todo.");
                    }

                    tasks.add(new ToDo(dex));
                    saveTasks(tasks);
                    System.out.println("Got it, I've added this task");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (line.startsWith("deadline")) {
                    if (line.length() <= 9) {
                        throw new BlondeBlazerException("Wait, you can't just say deadline without a clear stuff! State what you're gonna do after deadline.");
                    }

                    String[] parts = line.substring(9).split(" /by ");

                    if (parts[0].isEmpty() || parts[1].isEmpty()) {
                        throw new BlondeBlazerException("Wait, you can't just say deadline without a clear stuff! State what you're gonna do after deadline.");
                    }

                    tasks.add(new Deadline(parts[0], parts[1]));
                    saveTasks(tasks);
                    System.out.println("Got it, I've added this task");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (line.startsWith("event")) {

                    if (line.length() <= 6) {
                        throw new BlondeBlazerException("Wait, you can't just say event without a clear stuff! State what you're gonna do after event.");
                    }

                    String[] parts = line.substring(6).split(" /from ");
                    String dex = parts[0];
                    String[] times = parts[1].split(" /to ");

                    if (dex.isEmpty() || times[0].isEmpty() || times[1].isEmpty()) {
                        throw new BlondeBlazerException("Wait, you can't just say event without a clear stuff! State what you're gonna do after event.");
                    }

                    tasks.add(new Event(dex, times[0], times[1]));
                    saveTasks(tasks);
                    System.out.println("Got it, I've added this task");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (line.startsWith("delete")) {
                   if (line.length() <= 7) {
                       throw new BlondeBlazerException("Delete what? At least give me a number.");
                   }

                   String num = line.substring(7);
                   int index = Integer.parseInt(num) - 1;
                   Task removed = tasks.remove(index);
                   saveTasks(tasks);

                   System.out.println("Noted. I've removed this task:");
                   System.out.println(removed);
                   System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (line.startsWith("on")) {
                    if (line.length() <= 3) {
                        throw new BlondeBlazerException("Command 'on' should be followed with a date.");
                    }

                    String date = line.substring(3);
                    LocalDate targetDate;

                    try {
                        targetDate = LocalDate.parse(date);
                    } catch (Exception e) {
                        throw new BlondeBlazerException("Invalid date format!");
                    }

                    System.out.println("Here are the task on " + targetDate + ":");

                    boolean found = false;
                    int index = 1;

                    for (Task task : tasks) {
                        if (task instanceof Deadline) {
                            Deadline d = (Deadline) task;
                            if(d.getBy().equals(targetDate)) {
                                System.out.println(index + ". " + d.toString());
                                found = true;
                            }
                        }
                        index++;
                    }

                    if (!found) {
                        System.out.println("No tasks found on that date.");
                    }
                }
                else {
                    throw new Exception("Come on, I don't even know what does this mean!");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println(logo);
        }
    }

    abstract static class Task {
        protected String taskName;
        protected boolean isDone;

        Task(String taskName) {
            this.taskName = taskName;
            this.isDone = false;
        }

        void mark() {
            this.isDone = true;
        }

        void unmark() {
            this.isDone = false;
        }

        String getStatus() {
            return isDone ? "[X]" : "[ ]";
        }

        protected abstract String getType();

        @Override
        public String toString() {
            return getType() + getStatus() + " " + taskName;
        }
    }

    static class ToDo extends Task {
        ToDo(String taskName) {
            super(taskName);
        }

        @Override
        protected String getType() {
            return "[T]";
        }
    }

    static class Event extends Task {
        protected String from;
        protected String to;

        Event(String taskName, String from, String to) {
            super(taskName);
            this.from = from;
            this.to = to;
        }

        @Override
        protected String getType() {
            return "[E]";
        }

        @Override
        public String toString() {
            return getType() + getStatus() + " " + taskName + " (from: " + from + " to: " + to + ")";
        }
    }

    static class Deadline extends Task {
        protected LocalDate by;

        Deadline(String taskName, String by) throws BlondeBlazerException {
            super(taskName);
            try {
                this.by = LocalDate.parse(by.trim());
            } catch (Exception e) {
                throw new BlondeBlazerException("Invalid Date format! Use yyyy-mm-dd!");
            }
        }

        public LocalDate getBy() {
            return by;
        }

        @Override
        protected String getType() {
            return "[D]";
        }

        @Override
        public String toString() {
            return getType() + getStatus() + " " + taskName + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +  ")";
        }
    }

    public static class BlondeBlazerException extends Exception {
        public BlondeBlazerException(String message) {
            super(message);
        }
    }
}
