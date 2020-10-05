package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ALICE = new TaskBuilder().withTitle("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withDescription("alice,example.com")
            .withDateTime("01-01-2020 12:00")
            .withTags("friends").build();
    public static final Task BENSON = new TaskBuilder().withTitle("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withDescription("johnd,example.com").withDateTime("02-02-2020 12:00")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new TaskBuilder().withTitle("Carl Kurz").withDateTime("03-03-2020 12:00")
            .withDescription("heinz,example.com").withAddress("wall street").build();
    public static final Task DANIEL = new TaskBuilder().withTitle("Daniel Meier").withDateTime("04-04-2020 12:00")
            .withDescription("cornelia,example.com").withAddress("10th street").withTags("friends").build();
    public static final Task ELLE = new TaskBuilder().withTitle("Elle Meyer").withDateTime("05-05-2020 12:00")
            .withDescription("werner,example.com").withAddress("michegan ave").build();
    public static final Task FIONA = new TaskBuilder().withTitle("Fiona Kunz").withDateTime("06-06-2020 12:00")
            .withDescription("lydia,example.com").withAddress("little tokyo").build();
    public static final Task GEORGE = new TaskBuilder().withTitle("George Best").withDateTime("07-07-2020 12:00")
            .withDescription("anna,example.com").withAddress("4th street").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withTitle("Hoon Meier").withDateTime("08-08-2020 12:00")
            .withDescription("stefan,example.com").withAddress("little india").build();
    public static final Task IDA = new TaskBuilder().withTitle("Ida Mueller").withDateTime("09-09-2020 12:00")
            .withDescription("hans,example.com").withAddress("chicago ave").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withTitle(VALID_TITLE_AMY).withDateTime(VALID_DATE_TIME_AMY)
            .withDescription(VALID_DESCRIPTION_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withTitle(VALID_TITLE_BOB).withDateTime(VALID_DATE_TIME_BOB)
            .withDescription(VALID_DESCRIPTION_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
