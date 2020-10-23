package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TITLE = new Prefix("title:");
    public static final Prefix PREFIX_DATE_TIME = new Prefix("date:");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("desc:");
    public static final Prefix PREFIX_TYPE = new Prefix("type:");
    public static final Prefix PREFIX_STATUS = new Prefix("status:");
    public static final Prefix PREFIX_DAY = new Prefix("day:");
    public static final Prefix PREFIX_START_TIME = new Prefix("from:");
    public static final Prefix PREFIX_END_TIME = new Prefix("to:");
    public static final Prefix PREFIX_START_DATE = new Prefix("start:");
    public static final Prefix PREFIX_END_DATE = new Prefix("end:");
    public static final Prefix PREFIX_TAG = new Prefix("tag:");

}
