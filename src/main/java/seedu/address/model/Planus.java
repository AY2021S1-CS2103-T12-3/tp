package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class Planus implements ReadOnlyPlanus {

    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new UniqueTaskList();
    }

    public Planus() {}

    /**
     * Creates an Planus using the Tasks in the {@code toBeCopied}
     */
    public Planus(ReadOnlyPlanus toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code Planus} with {@code newData}.
     */
    public void resetData(ReadOnlyPlanus newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the PlaNUS task list.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to PlaNus.
     * The task must not already exist in the PlaNus task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the PlaNus task list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Mark the given tasks {@code targets} in the list as done.
     * task in targets must exist in the address book.
     */
    public void markAsDone(Task[] targets) {
        requireNonNull(targets);
        tasks.markAsDone(targets);
    }

    /**
     * Removes {@code key} from this {@code Planus}.
     * {@code key} must exist in PlaNus.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Planus // instanceof handles nulls
                && tasks.equals(((Planus) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
