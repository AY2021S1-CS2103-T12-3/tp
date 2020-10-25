package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

public class PlanusTest {

    private final Planus planus = new Planus();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), planus.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> planus.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPlanus_replacesData() {
        Planus newData = getTypicalPlanus();
        planus.resetData(newData);
        assertEquals(newData, planus);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(ALICE).withType(VALID_TYPE_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Task> newTasks = Arrays.asList(ALICE, editedAlice);
        PlanusStub newData = new PlanusStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> planus.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> planus.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInPlanus_returnsFalse() {
        assertFalse(planus.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInPlanus_returnsTrue() {
        planus.addTask(ALICE);
        assertTrue(planus.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInPlanus_returnsTrue() {
        planus.addTask(ALICE);
        Task editedAlice = new TaskBuilder(ALICE).withType(VALID_TYPE_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(planus.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> planus.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyPlanus whose tasks list can violate interface constraints.
     */
    private static class PlanusStub implements ReadOnlyPlanus {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();
        private final ObservableList<Task> calendar = FXCollections.observableArrayList();

        PlanusStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<Lesson> getLessonList() {
            return lessons;
        }

        @Override
        public ObservableList<Task> getCalendarList() {
            return calendar;
        }
    }

}
