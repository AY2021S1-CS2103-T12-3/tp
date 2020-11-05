package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Planus;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TITLE_AMY = "Amy Bee";
    public static final String VALID_TITLE_BOB = "Bob Choo";
    public static final String VALID_DATE_TIME_AMY = "31-12-2020 17:00";
    public static final String VALID_DATE_TIME_BOB = "01-01-2020 00:00";
    public static final String VALID_DESCRIPTION_AMY = "amy,example.com";
    public static final String VALID_DESCRIPTION_BOB = "bob,example.com";
    public static final String VALID_TYPE_BOB = "deadline";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";


    public static final String TITLE_DESC_AMY = " " + PREFIX_TITLE + VALID_TITLE_AMY;
    public static final String TITLE_DESC_BOB = " " + PREFIX_TITLE + VALID_TITLE_BOB;
    public static final String DATE_TIME_DESC_AMY = " " + PREFIX_DATE_TIME + VALID_DATE_TIME_AMY;
    public static final String DATE_TIME_DESC_BOB = " " + PREFIX_DATE_TIME + VALID_DATE_TIME_BOB;
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AMY;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "Homework&"; // '&' not allowed in titles
    public static final String INVALID_DATE_TIME_DESC = " " + PREFIX_DATE_TIME
            + "32-11-2000 19:00"; // 32nd day not allowed
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + "bob@yahoo"; // '@' not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    //valid lesson
    public static final String VALID_TITLE_CS2103T = "CS2103T Lecture";
    public static final String VALID_DESC_CS2103T = "Best lecture ever!";
    public static final String VALID_DAY_CS2103T = "Monday";
    public static final String VALID_START_TIME_CS2103T = "12:00";
    public static final String VALID_END_TIME_CS2103T = "14:00";
    public static final String VALID_START_DATE_CS2103T = "01-01-2020";
    public static final String VALID_END_DATE_CS2103T = "01-11-2020";
    public static final String VALID_TAG_CS2103T = "CS2103T";
    //valid lesson
    public static final String VALID_TITLE_CS2100 = "CS2100 Lecture";
    public static final String VALID_DESC_CS2100 = "Cool lecture!";
    public static final String VALID_DAY_CS2100 = "Tuesday";
    public static final String VALID_START_TIME_CS2100 = "14:00";
    public static final String VALID_END_TIME_CS2100 = "16:00";
    public static final String VALID_START_DATE_CS2100 = "01-11-2020";
    public static final String VALID_END_DATE_CS2100 = "01-12-2020";
    public static final String VALID_TAG_CS2100 = "CS2100";
    //invalid lesson
    public static final String INVALID_DAY_CS2103T = "ajhsf";
    public static final String INVALID_START_TIME_CS2103T = "14:60";
    public static final String INVALID_END_TIME_CS2103T = "16:60";
    public static final String INVALID_START_DATE_CS2103T = "32-11-2020";
    public static final String INVALID_END_DATE_CS2103T = "01-13-2020";
    //invalid lesson
    public static final String INVALID_DAY_CS2100 = "ajhsf";
    public static final String INVALID_START_TIME_CS2100 = "14:60";
    public static final String INVALID_END_TIME_CS2100 = "16:60";
    public static final String INVALID_START_DATE_CS2100 = "32-11-2020";
    public static final String INVALID_END_DATE_CS2100 = "01-13-2020";
    //valid event
    public static final String VALID_TITLE_EXPERIMENT = "Science experiment";
    public static final String VALID_DESC_EXPERIMENT = "Do grape experiment";
    public static final String VALID_DATE_EXPERIMENT = "01-01-2020";
    public static final String VALID_START_TIME_EXPERIMENT = "10:00";
    public static final String VALID_END_TIME_EXPERIMENT = "12:00";
    public static final String VALID_TAG_EXPERIMENT = "LSM1301";
    //valid event
    public static final String VALID_TITLE_MEETING = "Project meeting";
    public static final String VALID_DESC_MEETING = "Important meeting";
    public static final String VALID_DATE_MEETING = "01-01-2020";
    public static final String VALID_START_TIME_MEETING = "20:00";
    public static final String VALID_END_TIME_MEETING = "22:00";
    public static final String VALID_TAG_MEETING = "CS2101";
    //invalid event
    public static final String INVALID_DATE_MEETING = "32-01-2020";
    public static final String INVALID_START_TIME_MEETING = "20:60";
    public static final String INVALID_END_TIME_MEETING = "25:60";
    //valid deadline
    public static final String VALID_TITLE_LAB = "Do weekly lab assignment";
    public static final String VALID_DESC_LAB = "Prepare for demo during tutorial";
    public static final String VALID_DATETIME_LAB = "01-01-2020 23:59";
    public static final String VALID_TAG_LAB = "CS2100";
    //valid deadline
    public static final String VALID_TITLE_ESSAY = "Do final essay";
    public static final String VALID_DESC_ESSAY = "At least 800 words";
    public static final String VALID_DATETIME_ESSAY = "01-01-2020 23:59";
    public static final String VALID_TAG_ESSAY = "ES2660";
    //invalid deadline
    public static final String INVALID_DATETIME_LAB = "01-13-2020 23:59";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Planus expectedPlanus = new Planus(actualModel.getPlanus());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPlanus, actualModel.getPlanus());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s task list.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitTitle = task.getTitle().title.split("\\s+");
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, splitTitle[0]);
        model.updateFilteredTaskList(predicate);

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
