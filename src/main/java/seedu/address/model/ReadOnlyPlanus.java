package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of an PlaNus.
 */
public interface ReadOnlyPlanus {

    /**
     * Returns an unmodifiable view of the task list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the lesson list.
     * This list will not contain any duplicate lessons.
     */
    ObservableList<Lesson> getLessonList();

    /**
     * Returns an unmodifiable view of the lesson list.
     * This list will not contain any duplicate lessons.
     */
    ObservableList<Task> getCalendarList();
}
