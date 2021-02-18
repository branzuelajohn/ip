package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;


public class Parser {

    /**
     *  Returns the command to be executed
     *  If the command is invalid, DukeException is returned.
     *
     * @param command The input command from the user.
     * @return The command to be executed.
     * @throws DukeException when the command is invalid.
     */
    public static Command parse(String command) throws DukeException {
        String[] commandArr = command.trim().toLowerCase().split(" ");
        command = command.trim();
        switch (commandArr[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(command);
        case "todo":
            return new ToDoCommand(command);
        case "deadline":
            return new DeadlineCommand(command);
        case "event":
            return new EventCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "find":
            return new FindCommand(command);
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
