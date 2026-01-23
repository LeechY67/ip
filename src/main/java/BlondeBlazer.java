import java.util.Scanner;

public class BlondeBlazer {
    public static void main(String[] args) {
        String logo = "---------------------------------------------";
        System.out.println(logo + "\n" + "Hello! I'm BlondeBlazer!");
        System.out.println("What can I do for you?");
        System.out.println(logo);

        String[] tasks = new String[100];
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

            if (line.equals("list")) {
                if (taskCount == 0) {
                    System.out.println("Well, I got nothing to list out. Try add something.");
                }

                else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
            }

            else {
                tasks[taskCount] = line;
                taskCount++;
                System.out.println("added: " + line);
            }

            System.out.println(logo);
        }
    }
}
