package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Planus;
import seedu.address.model.ReadOnlyPlanus;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;
import seedu.address.model.task.Type;

/**
 * An Immutable Planus that is serializable to JSON format.
 */
@JsonRootName(value = "planus")
class JsonSerializablePlanus {

    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate task(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();
    private final List<JsonAdaptedTask> calendar = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePlanus} with the given tasks.
     */
    @JsonCreator
    public JsonSerializablePlanus(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyPlanus} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePlanus}.
     */
    public JsonSerializablePlanus(ReadOnlyPlanus source) {
        Type lessonType = new Type("lesson");
        // avoid storing tasks with type lesson
        tasks.addAll(source.getTaskList().stream().filter(task -> !task.getType().equals(lessonType))
                .map(JsonAdaptedTask::new).collect(Collectors.toList()));
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new).collect(Collectors.toList()));
        calendar.addAll(source.getCalendarList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this PlaNus into the model's {@code Planus} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Planus toModelType() throws IllegalValueException {
        Planus planus = new Planus();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (planus.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            planus.addTask(task);
            if (task.getType().equals("event")) {
                planus.addTaskToCalendar(task);
            }
        }

        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            ArrayList<Task> tasks = lesson.createRecurringTasks();

            for (Task task : tasks) {
                if (planus.hasTask(task)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
                }
                planus.addTask(task);
                planus.addTaskToCalendar(task);
            }
            planus.addLesson(lesson);
        }
        return planus;
    }

}
