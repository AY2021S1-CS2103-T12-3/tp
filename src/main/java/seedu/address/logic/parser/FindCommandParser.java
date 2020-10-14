package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DATE_TIME,
                                            PREFIX_DESCRIPTION, PREFIX_TYPE, PREFIX_STATUS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE,
                PREFIX_DESCRIPTION, PREFIX_DATE_TIME, PREFIX_TYPE, PREFIX_STATUS, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            predicate.setKeyword(PREFIX_TITLE, argMultimap.getValue(PREFIX_TITLE).get());
        }
        if (argMultimap.getValue(PREFIX_DATE_TIME).isPresent()) {
            predicate.setKeyword(PREFIX_DATE_TIME, argMultimap.getValue(PREFIX_DATE_TIME).get());
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            predicate.setKeyword(PREFIX_DESCRIPTION, argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            predicate.setKeyword(PREFIX_TYPE, argMultimap.getValue(PREFIX_TYPE).get());
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            predicate.setKeyword(PREFIX_STATUS, argMultimap.getValue(PREFIX_STATUS).get());
        }

        return new FindCommand(predicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
