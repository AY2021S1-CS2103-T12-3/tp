package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Task's Status in the PlaNus.
 * Guarantees: immutable;
 */
public class Status {


    public static final String MESSAGE_CONSTRAINTS =
            "the value is from the enum class State";
    public final State value;

    /**
     * Constructs a {@code Status}.
     *
     * @param state of the task
     */
    public Status(State state) {
        requireNonNull(state);
        this.value = state;
    }

    /**
     * Constructs a default {@code Status} that have a state of pending.
     */
    private Status() {
        value = State.INCOMPLETE;
    }

    /**
     * Returns a default Status object which have a pending state
     */
    public static Status defaultStatus() {
        return new Status();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value.equals(((Status) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
