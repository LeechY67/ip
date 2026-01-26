import java.util.Scanner;

public class BlondeBlazer {
    public static void main(String[] args) {
        String logo = "---------------------------------------------";
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

            else if (line.startsWith("mark ")) {
                int index = Integer.parseInt(line.substring(5)) - 1;
                tasks[index].mark();
                System.out.println("Nice, I've marked this task as done!");
                System.out.println((index + 1) + ". " + tasks[index].toString());
            }

            else if (line.startsWith("unmark ")) {
                int index = Integer.parseInt(line.substring(7)) - 1;
                tasks[index].unmark();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println((index + 1) + ". " + tasks[index].toString());
            }

            else {
                tasks[taskCount] = new Task(line);
                taskCount++;
                System.out.println("added: " + line);
            }

            System.out.println(logo);
        }
    }

    static class Task {
        String taskName;
        boolean isDone;

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

        @Override
        public String toString() {
            return getStatus() + " " + taskName;
        }
    }
}
