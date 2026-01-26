import java.util.ArrayList;
import java.util.Scanner;

public class BlondeBlazer {
    public static void main(String[] args) {
        String logo = "----------------------------------------------------------------------------------------";
        System.out.println(logo + "\n" + "Hello! I'm BlondeBlazer!");
        System.out.println("What can I do for you?");
        System.out.println(logo);

        ArrayList<Task> tasks = new ArrayList<>();
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
                    System.out.println("Nice, I've marked this task as done!");
                    System.out.println((index + 1) + ". " + tasks.get(index).toString());

                } else if (line.startsWith("unmark")) {
                    if (line.length() <= 7) {
                        throw new BlondeBlazerException("Bro, I don't know what you're gonna unmark...");
                    }

                    int index = Integer.parseInt(line.substring(7)) - 1;
                    tasks.get(index).unmark();
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

                   System.out.println("Noted. I've removed this task:");
                   System.out.println(removed);
                   System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                }  else {
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
        private String from;
        private String to;

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
        private String by;

        Deadline(String taskName, String by) {
            super(taskName);
            this.by = by;
        }

        @Override
        protected String getType() {
            return "[D]";
        }

        @Override
        public String toString() {
            return getType() + getStatus() + " " + taskName + " (by: " + by +  ")";
        }
    }

    public static class BlondeBlazerException extends Exception {
        public BlondeBlazerException(String message) {
            super(message);
        }
    }
}
