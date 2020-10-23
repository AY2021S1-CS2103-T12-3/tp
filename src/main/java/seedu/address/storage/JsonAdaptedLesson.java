package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Day;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;


/**
 * Jackson-friendly version of {@link Lesson}.
 */
class JsonAdaptedLesson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedLesson.class);

    private final String title;
    private final String tag;
    private final String description;
    private final String dayOfWeek;
    private final String startTime;
    private final String endTime;
    private final String startDate;
    private final String endDate;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("title") String title, @JsonProperty("tag") String tag,
                             @JsonProperty("description") String description,
                             @JsonProperty("dayOfWeek") String dayOfWeek, @JsonProperty("startTime") String startTime,
                       @JsonProperty("endTime") String endTime, @JsonProperty("startDate") String startDate,
                             @JsonProperty("endDate") String endDate) {
        this.title = title;
        this.tag = tag;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        title = source.getTitle().title;
        tag = source.getTag().tagName;
        description = source.getDescription().value;
        dayOfWeek = source.getDay().toString();
        startTime = source.getStartTime().format(Time.FORMATTER);
        endTime = source.getEndTime().format(Time.FORMATTER);
        startDate = source.getStartDate().format(Date.FORMATTER);
        endDate = source.getEndDate().format(Date.FORMATTER);
        logger.info("PlaNus lesson with title: '" + title + "' successfully converted to adapted lesson object");
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);
        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Tag.class.getSimpleName()));
        }
        final Tag modelTag = new Tag(tag);
        // tentatively description field is not allowed to be empty
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }

        final Description modelDescription;
        if (description.equals("")) {
            logger.info("Description for lesson title: '" + title + "' is empty."
                    + "Creating a default description for it");
            modelDescription = Description.defaultDescription();
        } else if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        } else {
            modelDescription = new Description(description);
        }

        if (dayOfWeek == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateTime.class.getSimpleName()));
        }

        final Day modelDay = ParserUtil.parseDay(dayOfWeek);

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalTime.class.getSimpleName()));
        }

        final LocalTime modelStartTime = LocalTime.parse(startTime, Time.FORMATTER);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalTime.class.getSimpleName()));
        }

        final LocalTime modelEndTime = LocalTime.parse(endTime, Time.FORMATTER);

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDate.class.getSimpleName()));
        }

        final LocalDate modelStartDate = LocalDate.parse(startDate, Date.FORMATTER);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDate.class.getSimpleName()));
        }

        final LocalDate modelEndDate = LocalDate.parse(endDate, Date.FORMATTER);

        return new Lesson(modelTitle, modelTag, modelDescription, modelDay,
                modelStartTime, modelEndTime, modelStartDate, modelEndDate);
    }

}
