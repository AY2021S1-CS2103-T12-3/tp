package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.State;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.Type;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TITLE = "School work";
    public static final String DEFAULT_DATE_TIME = "01-01-2020 12:00";
    public static final String DEFAULT_DESCRIPTION = "6 midterms next week.";
    public static final String DEFAULT_TYPE = "todo";
    public static final State DEFAULT_STATUS = State.INCOMPLETE;

    private Title title;
    private DateTime dateTime;
    private Description description;
    private Type type;
    private Set<Tag> tags;
    private Status status;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        title = new Title(DEFAULT_TITLE);
        dateTime = new DateTime(DEFAULT_DATE_TIME);
        description = new Description(DEFAULT_DESCRIPTION);
        type = new Type(DEFAULT_TYPE);
        tags = new HashSet<>();
        status = new Status(DEFAULT_STATUS);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        dateTime = taskToCopy.getDateTime();
        description = taskToCopy.getDescription();
        type = taskToCopy.getType();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Task} that we are building.
     */
    public TaskBuilder withType(String type) {
        this.type = new Type(type);
        return this;
    }

    /**
     * Sets the {@code dateTime} of the {@code Task} that we are building.
     */
    public TaskBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Task} that we are building to be default DateTime.
     * Simulates the situation that the task is created without a DateTime field.
     */
    public TaskBuilder withDefaultDateTime() {
        this.dateTime = DateTime.defaultDateTime();
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building to be default description.
     * Simulates the situation that the task is created without a description field.
     */
    public TaskBuilder withDefaultDescription() {
        this.description = Description.defaultDescription();
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task}  that we are building.
     */
    public TaskBuilder withStatus(State state) {
        this.status = new Status(state);
        return this;
    }

    public Task build() {
        return new Task(title, dateTime, description, type, tags);
    }

}
