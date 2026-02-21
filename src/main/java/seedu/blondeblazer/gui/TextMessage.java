
package seedu.blondeblazer.gui;

import seedu.blondeblazer.task.Task;

public class TextMessage {
    private static final String LINE =
            "----------------------------------------------------------------------------------------";

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm seedu.blondeblazer.BlondeBlazer!");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Failed to load tasks, starting with an empty list.");
    }

    public void showListHeader() {
        System.out.println("Here are the tasks in your list: ");
    }

    public void showEmptyList() {
        System.out.println("Well, I got nothing to list out. Try add something.");
    }

    public void showMarked(Task task, int displayIndex) {
        System.out.println("Nice, I've marked this task as done!");
        System.out.println(displayIndex + ". " + task);
    }

    public void showUnmarked(Task task, int displayIndex) {
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(displayIndex + ". " + task);
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it, I've added this task");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTasksOnDateHeader(String dateStr) {
        System.out.println("Here are the task on " + dateStr + ":");
    }

    public void showNoTasksFoundOnDate() {
        System.out.println("No tasks found on that date.");
    }

    public void showFindResult() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void showNoFinding() {
        System.out.println("No related findings...");
    }
}

