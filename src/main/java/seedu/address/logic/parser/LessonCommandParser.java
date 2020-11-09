package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_ATTRIBUTES;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.DateTimeUtil.isStartDateBeforeEndDate;
import static seedu.address.commons.util.DateTimeUtil.isStartTimeBeforeEndTime;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.LessonCommand;
import seedu.address.logic.parser.exceptions.MultipleAttributesException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class LessonCommandParser implements Parser<LessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LessonCommand parse(String args) throws ParseException, MultipleAttributesException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_TAG, PREFIX_DESCRIPTION, PREFIX_DAY,
                        PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_TAG, PREFIX_DAY, PREFIX_START_DATE, PREFIX_END_DATE,
                PREFIX_START_TIME, PREFIX_END_TIME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "", LessonCommand.MESSAGE_USAGE));
        }
        if (Parser.argMultimapHasRepeatedAttributes(argMultimap, PREFIX_TITLE, PREFIX_DAY, PREFIX_DESCRIPTION,
                PREFIX_TAG, PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_START_TIME, PREFIX_END_TIME)) {
            throw new MultipleAttributesException(MESSAGE_MULTIPLE_ATTRIBUTES);
        }
        Description description = Description.defaultDescription();

        assert argMultimap.getValue(PREFIX_TITLE).isPresent() : "prefix title is missing";
        assert argMultimap.getValue(PREFIX_TAG).isPresent() : "prefix tag is missing";
        assert argMultimap.getValue(PREFIX_START_DATE).isPresent() : "prefix start is missing";
        assert argMultimap.getValue(PREFIX_END_DATE).isPresent() : "prefix end is missing";
        assert argMultimap.getValue(PREFIX_START_TIME).isPresent() : "prefix from is missing";
        assert argMultimap.getValue(PREFIX_END_TIME).isPresent() : "prefix to is missing";
        assert argMultimap.getValue(PREFIX_DAY).isPresent() : "prefix day is missing";
        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        LocalDate endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());
        LocalTime startTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_START_TIME).get());
        LocalTime endTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_END_TIME).get());
        DayOfWeek dayOfWeek = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());

        if (!isStartDateBeforeEndDate(startDate, endDate)) {
            throw new ParseException(DateTimeUtil.RANGE_CONSTRAINTS);
        }
        if (!isStartTimeBeforeEndTime(startTime, endTime)) {
            throw new ParseException(Time.RANGE_CONSTRAINTS);
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }
        requireAllNonNull(startDate, endDate, startTime, endTime, dayOfWeek);

        Lesson lesson = new Lesson(title, tag, description, dayOfWeek, startTime, endTime, startDate, endDate);
        return new LessonCommand(lesson);
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
