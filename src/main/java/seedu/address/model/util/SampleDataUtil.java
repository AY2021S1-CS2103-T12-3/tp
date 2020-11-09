package seedu.address.model.util;

import static seedu.address.commons.util.DateTimeUtil.DATE_FORMATTER;
import static seedu.address.commons.util.DateTimeUtil.TIME_FORMATTER;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.Planus;
import seedu.address.model.ReadOnlyPlanus;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.deadline.DeadlineDateTime;
import seedu.address.model.task.deadline.DoneDateTime;
import seedu.address.model.task.deadline.Duration;
import seedu.address.model.task.deadline.Status;
import seedu.address.model.task.event.EndDateTime;
import seedu.address.model.task.event.Event;
import seedu.address.model.task.event.StartDateTime;


/**
 * Contains utility methods for populating {@code Planus} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        String event1Date = "19-11-2020";
        String event2Date = "25-11-2020";
        return new Task[] {
            Deadline.createDeadline(new Title("Borrow books"), new DeadlineDateTime("25-11-2020 09:00"),
                    Description.defaultDescription(), new Tag("CS1101S")), // incomplete deadline
            new Deadline(new Title("Assignment 1"), new DeadlineDateTime("25-11-2020 09:00"),
                    new Description("Programming Assignment 2 of CS3230, Very hard."),
                    new Tag("CS3230"), Status.createCompleteStatus(),
                    new Duration(20), DoneDateTime.createDoneNow()), // completed deadline
            Event.createUserEvent(new Title("Source Academy Avenger Meeting"),
                    new StartDateTime(event1Date + " 13:00"),
                    new EndDateTime(event1Date + " 18:00"), Description.defaultDescription(),
                    new Tag("CS1101S")), // event 1
            Event.createUserEvent(new Title("CS2105 project"),
                    new StartDateTime(event2Date + " 13:00"),
                    new EndDateTime(event2Date + " 15:00"), Description.defaultDescription(),
                    new Tag(("CS2105"))) // event 2
        };
    }

    public static Lesson[] getSampleLessons() {
        return new Lesson[] {
            new Lesson(new Title("Tutorial"), new Tag("CS1101S"),
                    new Description("I love functional programming!"), DayOfWeek.MONDAY,
                    LocalTime.parse("12:00", TIME_FORMATTER), LocalTime.parse("14:00", TIME_FORMATTER),
                    LocalDate.parse("01-08-2020", DATE_FORMATTER),
                    LocalDate.parse("01-12-2020", DATE_FORMATTER)),
            new Lesson(new Title("Lab"), new Tag("CS2100"), new Description("Logic Gates!"),
                    DayOfWeek.THURSDAY, LocalTime.parse("16:00", TIME_FORMATTER),
                    LocalTime.parse("17:00", TIME_FORMATTER),
                    LocalDate.parse("01-08-2020", DATE_FORMATTER),
                    LocalDate.parse("01-12-2020", DATE_FORMATTER)),
            new Lesson(new Title("Lecture"), new Tag("CS2105"),
                    new Description("Protocols"), DayOfWeek.MONDAY,
                    LocalTime.parse("14:00", TIME_FORMATTER), LocalTime.parse("16:00", TIME_FORMATTER),
                    LocalDate.parse("01-08-2020", DATE_FORMATTER),
                    LocalDate.parse("01-12-2020", DATE_FORMATTER))
        };
    }

    public static ReadOnlyPlanus getSamplePlanus() {
        Planus samplePlanus = new Planus();
        for (Task sampleTask : getSampleTasks()) {
            samplePlanus.addTask(sampleTask);
        }
        for (Lesson sampleLesson : getSampleLessons()) {
            samplePlanus.addLesson(sampleLesson);
        }
        return samplePlanus;
    }



}
