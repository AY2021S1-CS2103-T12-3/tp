package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_INVALID_DISPLAYED_INDEX_INFER = "Perhaps you made a typo?";
    public static final String MESSAGE_INVALID_DISPLAYED_INDEX = "The %1$s index provided does not exist in the "
            + "%1$s list. " + MESSAGE_INVALID_DISPLAYED_INDEX_INFER;
    public static final String MESSAGE_INVALID_DISPLAYED_INDEXES = "One or more %1$s indexes do not exist in the "
            + "%1$s list. " + MESSAGE_INVALID_DISPLAYED_INDEX_INFER;
    public static final String MESSAGE_DUPLICATE_INDEX = "Please do not include duplicate indexes.";

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! %1$s\n%2$s";
    public static final String MESSAGE_EMPTY_SEARCH_PHRASE = "Search phrase cannot be empty!";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX =
            String.format(MESSAGE_INVALID_DISPLAYED_INDEX, "task");
    public static final String MESSAGE_INVALID_TASKS_DISPLAYED_INDEX =
            String.format(MESSAGE_INVALID_DISPLAYED_INDEXES, "task");
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX =
            String.format(MESSAGE_INVALID_DISPLAYED_INDEX, "lesson");
    public static final String MESSAGE_INVALID_LESSONS_DISPLAYED_INDEX =
            String.format(MESSAGE_INVALID_DISPLAYED_INDEXES, "lesson");
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_LESSONS_LISTED_OVERVIEW = "%1$d lessons listed!";
    public static final String MESSAGE_INCORRECT_TASK_STATUS = "One or more targeted deadline is already completed.\n"
            + "Please check your command carefully.";
    public static final String MESSAGE_INVALID_DONE_TASK_TYPE = "You can only mark a deadline as done.\n"
            + "One or more task selected is not in deadline type";
    public static final String MESSAGE_INVALID_EVENT_EDIT_TYPE = "You cannot edit system generated lesson event";
    public static final String MESSAGE_INVALID_EDIT_TYPE = "You cannot edit this task with the given attributes";
    public static final String MESSAGE_INVALID_DEADLINE_EDIT_STATUS = "You cannot edit a deadline after marked as done";
    public static final String MESSAGE_START_BEFORE_END = "start time is cannot be before end time!";

}
