package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Title title;
    private final DateTime dateTime;
    private final Description description;

    // Data fields
    private final Type type;
    private final Set<Tag> tags = new HashSet<>();
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Task(Title title, DateTime dateTime, Description description, Type type, Set<Tag> tags) {
        requireAllNonNull(title, dateTime, description, type, tags);
        this.title = title;
        this.dateTime = dateTime;
        this.description = description;
        this.type = type;
        this.tags.addAll(tags);
        this.status = Status.defaultStatus();
    }

    /**
     * Every field must be present and not null.
     */
    public Task(Title title, DateTime dateTime, Description description, Type type, Set<Tag> tags,
                Status status) {
        requireAllNonNull(title, dateTime, description, type, tags);
        this.title = title;
        this.dateTime = dateTime;
        this.description = description;
        this.type = type;
        this.tags.addAll(tags);
        this.status = status;
    }

    public Title getTitle() {
        return title;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Description getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns true if both tasks of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTitle().equals(getTitle())
                && (otherTask.getDateTime().equals(getDateTime())
                || otherTask.getDescription().equals(getDescription()));
    }

    public Task markAsDone() {
        return new Task(title, dateTime, description, type, tags, new Status(State.COMPLETE));
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTitle().equals(getTitle())
                && otherTask.getDateTime().equals(getDateTime())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getType().equals(getType())
                && otherTask.getTags().equals(getTags())
                && otherTask.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, dateTime, description, type, tags, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Date: ")
                .append(getDateTime())
                .append(" Description: ")
                .append(getDescription())
                .append(" Type: ")
                .append(getType())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Status: ").append(getStatus());
        return builder.toString();
    }

}
