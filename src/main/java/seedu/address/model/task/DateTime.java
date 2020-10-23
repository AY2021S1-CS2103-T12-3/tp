package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Time;
/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final String MESSAGE_CONSTRAINTS =
            "DateTime should be in the format of dd-MM-yyyy HH:mm.";
    public static final String SEARCH_CONSTRAINTS =
            "Search phrase for date should be in the format of dd-MM-yyyy or HH:mm or dd-MM-yyyy HH:mm.";
    public static final String VALIDATION_REGEX =
            "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4} (2[0-3]|[01][0-9]):([0-5][0-9])$";
    public static final LocalDateTime DEFAULT_DATETIME = LocalDateTime.parse("01-01-1000 00:00", FORMATTER);
    public final LocalDateTime value;
    public final boolean isDefault;
    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(dateTime, FORMATTER);
        isDefault = false;
    }

    /**
     * Constructs a default {@code DateTime}.
     *
     * Caveat: Only called by defaultDateTime method.
     */
    private DateTime() {
        value = DEFAULT_DATETIME; // a dummy value
        isDefault = true;
    }

    /**
     * Returns true if a given string is a valid dateTime number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid search phrase for date time.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidSearchPhrase(String test) {
        return isValidDateTime(test) || Date.isValidDate(test) || Time.isValidTime(test);
    }

    /**
     * Constructs an empty DateTime object if the user does not provide the dateTime field.
     * Caveat: Only called when the user does not key in this field.
     */
    public static DateTime defaultDateTime() {
        return new DateTime();
    }

    @Override
    public String toString() {
        if (isDefault) {
            assert value.equals(DEFAULT_DATETIME) : "default datetime using real date time value.";
            return "";
        }
        return value.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && (value.equals(((DateTime) other).value)
                    || isDefault && ((DateTime) other).isDefault)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
