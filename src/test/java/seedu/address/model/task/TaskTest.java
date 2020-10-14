package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(ALICE.isSameTask(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTask(null));

        // different dateTime and description -> returns false
        Task editedAlice = new TaskBuilder(ALICE).withDateTime(VALID_DATE_TIME_BOB)
                                                 .withDescription(VALID_DESCRIPTION_BOB)
                                                 .build();
        assertFalse(ALICE.isSameTask(editedAlice));

        // different title -> returns false
        editedAlice = new TaskBuilder(ALICE).withTitle(VALID_TITLE_BOB).build();
        assertFalse(ALICE.isSameTask(editedAlice));

        // same title, same dateTime, different attributes -> returns true
        editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).withType(VALID_TYPE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameTask(editedAlice));

        // same title, same type, different attributes -> returns true

        editedAlice = new TaskBuilder(ALICE).withDateTime(VALID_DATE_TIME_BOB).withType(VALID_TYPE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameTask(editedAlice));

        // same title, same dateTime, same type, different attributes -> returns true
        editedAlice = new TaskBuilder(ALICE).withType(VALID_TYPE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameTask(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different task -> returns false
        assertFalse(ALICE.equals(BOB));

        // different title -> returns false
        Task editedAlice = new TaskBuilder(ALICE).withTitle(VALID_TITLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different dateTime -> returns false
        editedAlice = new TaskBuilder(ALICE).withDateTime(VALID_DATE_TIME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different description -> returns false
        editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different type -> returns false
        editedAlice = new TaskBuilder(ALICE).withType(VALID_TYPE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TaskBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
