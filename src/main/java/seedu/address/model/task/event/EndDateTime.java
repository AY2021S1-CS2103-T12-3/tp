package seedu.address.model.task.event;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.lesson.Time;
import seedu.address.model.task.DateTime;

/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class EndDateTime extends DateTime {

    public final boolean isNull;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public EndDateTime(String dateTime) {
        super(dateTime);
        if (dateTime.isEmpty() || dateTime.isBlank() || dateTime == null) {
            this.isNull = true;
        } else {
            isNull = false;
        }
    }

    /**
     * Factory method to create EndDateTime object
     * @param date of the endDateTime
     * @param time of the endDateTime
     * @return an EndDateTime object
     */
    public static EndDateTime createEndDateTime(String date, String time) {
        checkArgument(DateUtil.isValidDate(date), DateUtil.MESSAGE_CONSTRAINTS);
        checkArgument(DateUtil.isValidTime(time), DateUtil.MESSAGE_CONSTRAINTS);
        String datetime = date + " " + time;
        return new EndDateTime(datetime);
    }

    public boolean isNull() {
        return isNull;
    }

    public static boolean isValidDateTime(String date, String time) {
        return DateUtil.isValidDate(date) && DateUtil.isValidTime(time);
    }

    @Override
    public String toString() {
        if (isNull) {
            return "";
        } else {
            return value.format(DateUtil.DATETIME_FORMATTER);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndDateTime // instanceof handles nulls
                && (value.equals(((EndDateTime) other).value)
                    || isNull && ((EndDateTime) other).isNull())); // state check
    }
}
