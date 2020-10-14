package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the address book.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": mark the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX (one or more positive integer that is separate by a white space)\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_Done_TASK_SUCCESS = "Task: %1$s is done";

    private final Index[] targetIndexes;

    public DoneCommand(Index[] targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        Task[] tasksToMarkAsDone = new Task[targetIndexes.length];
        for (int i = 0; i < targetIndexes.length; i++) {
            if (targetIndexes[i].getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }
            tasksToMarkAsDone[i] = lastShownList.get(targetIndexes[i].getZeroBased());
        }
        model.markAsDone(tasksToMarkAsDone);
        return new CommandResult(buildMessage(tasksToMarkAsDone));
    }

    /**
     * @param tasks that is been marked as done
     * returns message built by the list of tasks done.
     */
    public static String buildMessage(Task[] tasks) {
        String message = "";
        for (int i = 0; i < tasks.length; i++) {
            message += String.format(MESSAGE_Done_TASK_SUCCESS, tasks[i]) + "\n";
        }
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && Arrays.equals(targetIndexes, ((DoneCommand) other).targetIndexes )); // state check
    }
}
