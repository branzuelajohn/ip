package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Event;
import duke.tasks.TaskList;

/**
 * Command to create an event
 */
public class EventCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    /**
     * Constructor method
     * @param input The input command from the user.
     */
    public EventCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    /**
     * Execute command for event command.
     * @param tasks The tasks in the TaskList.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @return the line to print.
     * @throws DukeException if the event command is missing description or the user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (input.length() <= 6 || !input.contains("/at")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty");
        }
        String[] strArr = input.split("/at ");
        String description = strArr[0].substring(6).trim();
        String date = strArr[1];
        Event e = new Event(description, date);
        tasks.addTask(e);
        storage.addNewDataToFile("E", "0", e.getDescription(), e.getDate());
        StringBuilder sb = new StringBuilder(ui.showTaskAdded(e) + ui.showNoOfItems(tasks));
        return sb.toString();
    }
}
