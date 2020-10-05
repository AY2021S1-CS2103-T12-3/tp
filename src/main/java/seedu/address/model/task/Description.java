package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the PlaNus task list.
 * Comprises of a few sentences representing a brief description of the content of the task.
 * Guarantees: is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Descriptions should only contain alphanumerical values"
            + "as well as comma, period, question mark and exclamation mark, "
            + "should also not be blank or start with space\n"
            + "example => desc:Tell Zijian to review my PR!!! Repeat, this is urgent!!! You get it?";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum},.!?][\\p{Alnum},.!? ]*";
    private static final String DEFAULT_DESCRIPTION = "";

    public final String value;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    /**
     * Constructs a default {@code Description}.
     * Caveat: Only called by defaultDescription method.
     */
    private Description() {
        value = DEFAULT_DESCRIPTION;
    }

    /**
     * Returns if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Construct an empty Description when user didn't provide the description field.
     * Caveat: Only called when the user didn't key in this field.
     */
    public static Description defaultDescription() {
        return new Description();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
