package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

/**
 * Command to create a deadline task.
 */
public class DeadlineCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    /**
     * Constructor method
     * @param input The user input command.
     */
    public DeadlineCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    /**
     * The execute method for deadline.
     * @param tasks The tasks in the TaskList.
     * @param ui Standard UI object
     * @param storage Standard storage object
     * @throws DukeException if the deadline command is missing a description or the user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (input.length() <= 9 || !input.contains("/by")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] strArr = input.split("/by ");
        String description = strArr[0].substring(9).trim();
        String by = strArr[1];
        Deadline d = new Deadline(description, by);
        tasks.addTask(d);
        storage.addNewDataToFile("D", "0", d.getDescription(), d.getBy());
        StringBuilder sb = new StringBuilder(ui.showTaskAdded(d) + "\n" + ui.showNoOfItems(tasks));
        return sb.toString();
    }
}
