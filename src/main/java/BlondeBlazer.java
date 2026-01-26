import java.util.Scanner;

public class BlondeBlazer {
    public static void main(String[] args) {
        String logo = "----------------------------------------------------------------------------------------";
        System.out.println(logo + "\n" + "Hello! I'm BlondeBlazer!");
        System.out.println("What can I do for you?");
        System.out.println(logo);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if (line.equals("bye")) {
                System.out.println(logo);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(logo);
                break;
            }

            else if (line.equals("list")) {
                if (taskCount == 0) {
                    System.out.println("Well, I got nothing to list out. Try add something.");
                }

                else {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i].toString());
                    }
                }
            }

            else if (line.startsWith("mark")) {
                if (line.length() <= 5) {
                    System.out.println("Wait, you can't just say mark without giving an actual number!");
                }

                else {
                    int index = Integer.parseInt(line.substring(5)) - 1;
                    tasks[index].mark();
                    System.out.println("Nice, I've marked this task as done!");
                    System.out.println((index + 1) + ". " + tasks[index].toString());
                }
            }

            else if (line.startsWith("unmark")) {
                if (line.length() <= 7) {
                    System.out.println("Wait, you can't just say unmark without giving an actual number!");
                }

                else {
                    int index = Integer.parseInt(line.substring(7)) - 1;
                    tasks[index].unmark();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println((index + 1) + ". " + tasks[index].toString());
                }
            }

            else if (line.startsWith("todo")) {
                if (line.length() <= 5) {
                    System.out.println("Wait, you can't just say todo without giving actual things!");
                }

                else {
                    String dex = line.substring(5);
                    tasks[taskCount] = new ToDo(dex);
                    System.out.println("Got it, I've added this task");
                    System.out.println(tasks[taskCount].toString());
                    System.out.println("Now you have " + ++taskCount + " tasks in the list.");
                }
            }

            else if (line.startsWith("deadline")) {
                if (line.length() <= 9) {
                    System.out.println("Wait, you can't just say deadline without giving actual things!");
                }
                else {
                    String[] parts = line.substring(9).split(" /by ");
                    tasks[taskCount] = new Deadline(parts[0], parts[1]);
                    System.out.println("Got it, I've added this task");
                    System.out.println(tasks[taskCount].toString());
                    System.out.println("Now you have " + ++taskCount + " tasks in the list.");
                }
            }

            else if (line.startsWith("event")) {
                if (line.length() <= 6) {
                    System.out.println("Wait, you can't just say event without giving actual things!");
                }
                else {
                    String[] parts = line.substring(6).split(" /from ");
                    String dex = parts[0];
                    String[] times = parts[1].split(" /to ");
                    tasks[taskCount] = new Event(dex, times[0], times[1]);
                    System.out.println("Got it, I've added this task");
                    System.out.println(tasks[taskCount].toString());
                    System.out.println("Now you have " + ++taskCount + " tasks in the list.");
                }
            }

            else {
                System.out.println("Come on, I don't even know what does this mean!");
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
}
