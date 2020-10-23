package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;

public class Day {
    public static final String MESSAGE_CONSTRAINTS =
            "Day should be in the format of MONDAY, TUESDAY, ..., SUNDAY (case-insensitive)";
    public final DayOfWeek day;
    /**
     * Constructs a {@code Day}.
     *
     * @param day A valid day.
     */
    public Day(String day) {
        requireNonNull(day);
        this.day = DayOfWeek.valueOf(day);
    }
    @Override
    public String toString() {
        return day.toString();
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && day.equals(((Day) other).day));
    }

    @Override
    public int hashCode() {
        return day.hashCode();
    }
}
