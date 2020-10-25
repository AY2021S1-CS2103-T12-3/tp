package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the PlaNus data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Planus planus;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Lesson> filteredLessons;
    private final FilteredList<Task> filteredCalendar;

    /**
     * Initializes a ModelManager with the given planus and userPrefs.
     */
    public ModelManager(ReadOnlyPlanus planus, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(planus, userPrefs);

        logger.fine("Initializing with PlaNus: " + planus + " and user prefs " + userPrefs);

        this.planus = new Planus(planus);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.planus.getTaskList());
        filteredLessons = new FilteredList<>(this.planus.getLessonList());
        filteredCalendar = new FilteredList<>(this.planus.getCalendarList());
    }

    public ModelManager() {
        this(new Planus(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPlanusFilePath() {
        return userPrefs.gePlanusFilePath();
    }

    @Override
    public void setPlanusFilePath(Path planusFilePath) {
        requireNonNull(planusFilePath);
        userPrefs.setPlanusFilePath(planusFilePath);
    }

    //=========== Planus ================================================================================

    @Override
    public void setPlanus(ReadOnlyPlanus planus) {
        this.planus.resetData(planus);
    }

    @Override
    public ReadOnlyPlanus getPlanus() {
        return planus;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return planus.hasTask(task);
    }

    @Override
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return planus.hasLesson(lesson);
    }

    @Override
    public void deleteTask(Task[] targets) {
        planus.removeTask(targets);
    }

    @Override
    public void markAsDone(Task[] targets) {
        planus.markAsDone(targets);
    }

    @Override
    public void addTask(Task task) {
        planus.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void addLesson(Lesson lesson) {
        planus.addLesson(lesson);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void addTaskToCalendar(Task task) {
        planus.addTaskToCalendar(task);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        planus.setTask(target, editedTask);
    }

    @Override
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireAllNonNull(target, editedLesson);

        planus.setLesson(target, editedLesson);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedPlanus}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Lesson} backed by the internal list of
     * {@code versionedPlanus}
     */
    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        return filteredLessons;
    }

    @Override
    public ObservableList<Task> getFilteredCalendarList() {
        return filteredCalendar;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void updateFilteredLessonList(Predicate<Lesson> predicate) {
        requireNonNull(predicate);
        filteredLessons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return planus.equals(other.planus)
                && userPrefs.equals(other.userPrefs)
                && filteredTasks.equals(other.filteredTasks)
                && filteredLessons.equals(other.filteredLessons)
                && filteredCalendar.equals(other.filteredCalendar);
    }

}
