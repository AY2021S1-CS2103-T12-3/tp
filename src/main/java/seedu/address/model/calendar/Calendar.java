package seedu.address.model.calendar;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;


public class Calendar implements Iterable<Task> {

    private final ObservableList<Task> calendarList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(calendarList);

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return calendarList.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        calendarList.add(toAdd);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = calendarList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        calendarList.set(index, editedTask);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!calendarList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Removes the equivalent tasks from the list.
     * The tasks must exist in the list.
     */
    public void remove(Task[] tasks) {
        requireNonNull(tasks);
        for (int i = 0; i < tasks.length; i++) {
            int index = calendarList.indexOf(tasks[i]);
            if (index == -1) {
                throw new TaskNotFoundException();
            }
            calendarList.remove(tasks[i]);
        }
    }

    /**
     * Replaces the task in the list with {@code tasks} done version.
     * {@code tasks} must not contain duplicate tasks.
     * each task in tasks must exist in the list.
     */
    public void markAsOver(Task[] tasks) {
        requireNonNull(tasks);
        for (int i = 0; i < tasks.length; i++) {
            int index = calendarList.indexOf(tasks[i]);
            if (index == -1) {
                throw new TaskNotFoundException();
            }
            calendarList.set(index, tasks[i].markAsDone());
        }
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Task> iterator() {
        return calendarList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Calendar // instanceof handles nulls
                && calendarList.equals(((Calendar) other).calendarList));
    }

    @Override
    public int hashCode() {
        return calendarList.hashCode();
    }

}
