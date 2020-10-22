package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;


/**
 * A list of lessons that enforces uniqueness between its elements and does not allow nulls.
 * A lesson is considered unique by comparing using {@code Task#isSameTasks(Task)}. As such, adding and updating of
 * lessons uses Lesson#isSameLessons(Lesson) for equality so as to ensure that the lesson being added or updated is
 * unique in terms of identity in the UniqueTasksList. However, the removal of a lesson uses Task#equals(Object) so
 * as to ensure that the lesson with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Lesson#isSameLesson(Lesson)
 */
public class UniqueLessonList implements Iterable<Lesson> {

    private final ObservableList<Lesson> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lesson> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent lesson as the given argument.
     */
    public boolean contains(Lesson toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLesson);
    }

    /**
     * Adds a lesson to the list.
     * The lesson must not already exist in the list.
     */
    public void add(Lesson toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the lesson {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The lesson identity of {@code editedTask} must not be the same as another existing lesson in the list.
     */
    public void setTask(Lesson target, Lesson editLesson) {
        requireAllNonNull(target, editLesson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameLesson(editLesson) && contains(editLesson)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, editLesson);
    }

    /**
     * Removes the equivalent lesson from the list.
     * The lesson must exist in the list.
     */
    public void remove(Lesson toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Removes the equivalent lessons from the list.
     * The lessons must exist in the list.
     */
    public void remove(Lesson[] lessons) {
        requireNonNull(lessons);
        for (int i = 0; i < lessons.length; i++) {
            int index = internalList.indexOf(lessons[i]);
            if (index == -1) {
                throw new TaskNotFoundException();
            }
            internalList.remove(lessons[i]);
        }
    }


    public void setTasks(UniqueLessonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code lessons}.
     * {@code lessons} must not contain duplicate lessons.
     */
    public void setLessons(List<Lesson> lessons) {
        requireAllNonNull(lessons);
        if (!lessonsAreUnique(lessons)) {
            throw new DuplicateTaskException();
        }

        internalList.setAll(lessons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lesson> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Lesson> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLessonList // instanceof handles nulls
                && internalList.equals(((UniqueLessonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code lessons} contains only unique lessons.
     */
    private boolean lessonsAreUnique(List<Lesson> lessons) {
        for (int i = 0; i < lessons.size() - 1; i++) {
            for (int j = i + 1; j < lessons.size(); j++) {
                if (lessons.get(i).isSameLesson(lessons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
