package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.BOB;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.TaskBuilder;


public class CalendarTest {

    private final Calendar calendarList = new Calendar();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(calendarList.contains(ALICE));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        calendarList.add(ALICE);
        assertTrue(calendarList.contains(ALICE));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        calendarList.add(ALICE);
        Task editedAlice = new TaskBuilder(ALICE).withType(VALID_TYPE_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(calendarList.contains(editedAlice));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        calendarList.add(ALICE);
        assertThrows(DuplicateTaskException.class, () -> calendarList.add(ALICE));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.setTask(null, ALICE));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.setTask(ALICE, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> calendarList.setTask(ALICE, ALICE));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        calendarList.add(ALICE);
        calendarList.setTask(ALICE, ALICE);
        Calendar expectedCalendarList = new Calendar();
        expectedCalendarList.add(ALICE);
        assertEquals(expectedCalendarList, calendarList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        calendarList.add(ALICE);
        Task editedAlice = new TaskBuilder(ALICE).withType(VALID_TYPE_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        calendarList.setTask(ALICE, editedAlice);
        Calendar expectedCalendarList = new Calendar();
        expectedCalendarList.add(editedAlice);
        assertEquals(expectedCalendarList, calendarList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        calendarList.add(ALICE);
        calendarList.setTask(ALICE, BOB);
        Calendar expectedCalendarList = new Calendar();
        expectedCalendarList.add(BOB);
        assertEquals(expectedCalendarList, calendarList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        calendarList.add(ALICE);
        calendarList.add(BOB);
        assertThrows(DuplicateTaskException.class, () -> calendarList.setTask(ALICE, BOB));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.remove((Task) null));
    }

    @Test
    public void remove_nullTasks_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.remove((Task[]) null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> calendarList.remove(ALICE));
    }

    @Test
    public void remove_existingTask_removesTask() {
        calendarList.add(ALICE);
        calendarList.remove(ALICE);
        Calendar expectedCalendarList = new Calendar();
        assertEquals(expectedCalendarList, calendarList);
    }

    @Test
    public void setTask_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> calendarList.setCalendarList((List<Task>) null));
    }

    @Test
    public void setTask_list_replacesOwnListWithProvidedList() {
        calendarList.add(ALICE);
        List<Task> taskList = Collections.singletonList(BOB);
        calendarList.setCalendarList(taskList);
        Calendar expectedCalendarList = new Calendar();
        expectedCalendarList.add(BOB);
        assertEquals(expectedCalendarList, calendarList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> calendarList.asUnmodifiableObservableList().remove(0));
    }
}
