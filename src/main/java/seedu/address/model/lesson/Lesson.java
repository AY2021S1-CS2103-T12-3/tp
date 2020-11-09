package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.TimeSlot;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.event.EndDateTime;
import seedu.address.model.task.event.Event;
import seedu.address.model.task.event.StartDateTime;


/**
 * Lesson class that stores information about a module's lessons.
 */
public class Lesson implements TimeSlot {
    private final Title title;
    private final Tag tag;
    private final Description description;
    private final DayOfWeek dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private ArrayList<Task> associatedTasks = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Lesson(Title title, Tag tag, Description description, DayOfWeek dayOfWeek, LocalTime startTime,
                  LocalTime endTime, LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(title, description, dayOfWeek, startTime, endTime, startDate, endDate);
        this.title = title;
        this.tag = tag;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Title getTitle() {
        return title;
    }
    public Description getDescription() {
        return description;
    }
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public LocalDateTime getStartDateTimeValue() {
        return LocalDateTime.of(getStartDate(), getStartTime());
    }
    public LocalDateTime getEndDateTimeValue() {
        return LocalDateTime.of(getEndDate(), getEndTime());
    }
    public Tag getTag() {
        return tag;
    }
    private int getTimeTaken() {
        return (int) Duration.between(startTime, endTime).toMinutes();
    }
    /**
     * Creates recurring a list of event tasks based on the lesson's details.
     * @return a list of recurring tasks to add.
     */
    public ArrayList<Task> createRecurringTasks() {
        LocalDate currentDate = offsetStartDate();
        ArrayList<Task> tasksToAdd = new ArrayList<>();
        while (!currentDate.isAfter(this.endDate)) {
            LocalDateTime localStartDateTime = LocalDateTime.of(currentDate, getStartTime());
            LocalDateTime localEndDateTime = LocalDateTime.of(currentDate, getEndTime());
            String startDateTimeString = localStartDateTime.format(DateTimeUtil.DATETIME_FORMATTER);
            String endDateTimeString = localEndDateTime.format(DateTimeUtil.DATETIME_FORMATTER);
            StartDateTime startDateTime = new StartDateTime(startDateTimeString);
            EndDateTime endDateTime = new EndDateTime(endDateTimeString);
            Event eventToAdd = Event.createLessonEvent(title, startDateTime, endDateTime, description, tag);
            tasksToAdd.add(eventToAdd);
            currentDate = currentDate.plusWeeks(1);
        }
        associatedTasks = tasksToAdd;
        return tasksToAdd;
    }

    /**
     * Calculates if a given time is within the start and end time of the lesson.
     * @param time a LocalTime object to be tested.
     * @return true if the given time is within the period of the lesson and false otherwise.
     */
    public boolean periodContainsGivenTime(LocalTime time) {
        return !(time.isBefore(startTime) || time.isAfter(endTime));
    }

    /**
     * Calculates if the lesson happens on the given date.
     * @param date the date to be tested.
     * @return true if the lesson happens on the given date and false otherwise.
     */
    public boolean happensOnDate(LocalDate date) {
        LocalDate currentDate = offsetStartDate();
        while (!currentDate.isAfter(this.endDate)) {
            if (currentDate.equals(date)) {
                return true;
            }
            currentDate = currentDate.plusWeeks(1);
        }
        return false;
    }

    /**
     * Calculates if a lesson happens on a given date time.
     * @param dateTime a LocalDateTime object to be tested.
     * @return true if the lesson happens on the date time and false otherwise.
     */
    public boolean happensOnDateTime(LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();
        if (!happensOnDate(date)) {
            return false;
        }
        return periodContainsGivenTime(time);
    }

    /**
     * Calculates the total number of hours this lesson takes within a specified time period.
     */
    public int timeTakenWithinPeriod(LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(startDate, endDate);
        return numberOfLessonsWithinPeriod(startDate, endDate) * getTimeTaken();
    }
    /**
     * Calculates the number of lessons within a specified time period.
     */
    private int numberOfLessonsWithinPeriod(LocalDate start, LocalDate end) {
        LocalDate currentDate = getStartDate();
        while (!((currentDate.getDayOfWeek()).equals(this.dayOfWeek)
            && ((currentDate.isAfter(start)) || currentDate.isEqual(start)))) {
            currentDate = currentDate.plusDays(1);
        }
        int counter = 0;
        while ((currentDate.isBefore(this.endDate) || currentDate.isEqual(this.endDate))
            && (currentDate.isAfter(start) || currentDate.isEqual(start))
            && (currentDate.isBefore(end) || currentDate.isEqual(end))) {
            counter++;
            currentDate = currentDate.plusDays(7);
        }
        return counter;
    }
    /**
     * Returns the set of tasks the lesson created.
     * @return an array list of tasks created by the lesson.
     */
    public ArrayList<Task> getAssociatedTasks() {
        return associatedTasks;
    }

    /**
     * Returns true if both lessons of the same attributes but may contain different descriptions.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }
        return otherLesson != null
                && otherLesson.getTitle().equals(getTitle())
                && otherLesson.getDayOfWeek().equals(getDayOfWeek())
                && otherLesson.getStartDate().equals(getStartDate())
                && otherLesson.getEndDate().equals(getEndDate())
                && otherLesson.getStartTime().equals(getStartTime())
                && otherLesson.getEndTime().equals(getEndTime())
                && otherLesson.getTag().equals(getTag());
    }
    /**
     * Returns true if both lessons have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Lesson)) {
            return false;
        }
        Lesson otherLesson = (Lesson) other;
        return otherLesson != null
                && otherLesson.getTitle().equals(getTitle())
                && otherLesson.getDayOfWeek().equals(getDayOfWeek())
                && otherLesson.getDescription().equals(getDescription())
                && otherLesson.getStartDate().equals(getStartDate())
                && otherLesson.getEndDate().equals(getEndDate())
                && otherLesson.getStartTime().equals(getStartTime())
                && otherLesson.getEndTime().equals(getEndTime())
                && otherLesson.getTag().equals(getTag());
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, tag, description, dayOfWeek, startTime, endTime, startDate, endDate);
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        // attributes that are neglected are hidden
        builder.append(getTitle())
                .append(getDescription().equals(Description.defaultDescription()) ? "" : " Description: ")
                .append(getDescription())
                .append(" Tag: ")
                .append(getTag())
                .append(" Day: ")
                .append(getDayOfWeek())
                .append(" Start Time: ")
                .append(getStartTime())
                .append(" End Time: ")
                .append(getEndTime())
                .append(" Start Date: ")
                .append(getStartDate().format(DateTimeUtil.DATE_FORMATTER))
                .append(" End Date: ")
                .append(getEndDate().format(DateTimeUtil.DATE_FORMATTER));
        return builder.toString();
    }

    /**
     * Offsets the start date to match the day of first lesson.
     * @return a LocalDate object representing the offset start date.
     */
    private LocalDate offsetStartDate() {
        LocalDate currentDate = getStartDate();
        while (!(currentDate.getDayOfWeek()).equals(this.dayOfWeek)) {
            currentDate = currentDate.plusDays(1);
        }
        return currentDate;
    }
}
