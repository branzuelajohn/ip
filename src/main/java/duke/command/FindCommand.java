package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;


/**
 * Command to find a task.
 */
public class FindCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    /**
     * Finds a task by searching for a keyword
     * @param input The input command from the user.
     */
    public FindCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    /**
     * Executes find command and prints list of tasks that match the command.
     * Tasks shown are numbered in ascending order starting from "1."
     * @param tasks The tasks in the TaskList.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @return String the line to print.
     * @throws DukeException if the user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String foundTasks = ui.showFoundTasks();
        StringBuilder sb = new StringBuilder(foundTasks + " \n");
        String[] strArr = input.split(" ");
        int count = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().contains(strArr[1])) {
                String toPrint = "    " + count + ". " + tasks.getTask(i).toString();
                count++;
                sb.append(toPrint);
            }
        }
        return sb.toString();
    }
}
