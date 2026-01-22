import java.util.Scanner;

public class BlondeBlazer {
    public static void main(String[] args) {
        String logo = "---------------------------------------------";
        System.out.println(logo + "\n" + "Hello! I'm BlondeBlazer!");
        System.out.println("What can I do for you?\n");
        System.out.println(logo);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if (line.equals("bye")) {
                System.out.println(logo + "\n" + "Bye. Hope to see you again soon!\n");
                System.out.println(logo);
                break;
            }

            System.out.println(logo);
            System.out.println(line + "\n");
            System.out.println(logo + "\n");
        }
    }
}
