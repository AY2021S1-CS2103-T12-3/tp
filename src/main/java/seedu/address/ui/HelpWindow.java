package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-t12-3.github.io/tp/UserGuide.html";

    public static final String HELP_MESSAGE = "The following is the set of commands you can use:\n"
            + " ____________________________________________________________________ \n"
            + "|  [Command] |                   [Format]                            |\n"
            + "|--------------------------------------------------------------------|\n"
            + "|    help    | help                                                  |\n"
            + "|--------------------------------------------------------------------|\n"
            + "|    event   | event title:TITLE [desc:DESCRIPTION] date:DATE        |\n"
            + "|            | from:TIME to:TIME tag:MODULE_CODE                     |\n"
            + "|            | e.g. event title:meeting date:23-10-2020 from:20:00   |\n"
            + "|            | to:22:00 tag:CS2103T                                  |\n"
            + "|--------------------------------------------------------------------|\n"
            + "|  deadline  | deadline title:TITLE [desc:DESCRIPTION]               |\n"
            + "|            | [datetime:DATETIME] tag:MODULE_CODE                   |\n"
            + "|            | e.g. deadline title:Assignment2 datetime:23-10-2020   |\n"
            + "|            | 18:00 tag:CS2103T                                     |\n"
            + "|--------------------------------------------------------------------|\n"
            + "|   lesson   | lesson title:TITLE tag:MODULE_CODE                    |\n"
            + "|            | [desc:DESCRIPTION] day:DAY from:TIME to:TIME          |\n"
            + "|            | start:DATE end:DATE                                   |\n"
            + "|            | e.g. lesson title:CS2103T tag:CS2103T desc: truly fun |\n"
            + "|            | day:Mon from:12:00 to:14:00 start:01-01-2020          |\n"
            + "|            | end:01-05-2020                                        |\n"
            + "|--------------------------------------------------------------------|\n"
            + "|   delete   | delete INDEX...                                       |\n"
            + "|            | e.g. delete 3, delete 3, 4, 5                         |\n"
            + "|--------------------------------------------------------------------|\n"
            + "|    done    | done INDEX:TIME_TAKEN...                              |\n"
            + "|            | e.g. done 1:20, done 1:20 2:60 3:120                  |\n"
            + "|--------------------------------------------------------------------|\n"
            + "|    find    | find ATTRIBUTE:SEARCH_PHRASE ...                      |\n"
            + "|            | e.g.find title:dinner type:deadline date:02-02-2020   |\n"
            + "|--------------------------------------------------------------------|\n"
            + "|    edit    | edit INDEX [title:TITLE] [date:DATE]                  |\n"
            + "|            | [desc:DESCRIPTION] [type:TYPE] [tag:MODULE_CODE]      |\n"
            + "|            | e.g. edit 1 date:02-02-2020 12:00 tag:CS2101          |\n"
            + "|--------------------------------------------------------------------|\n"
            + "|    exit    | exit                                                  |\n"
            + " -------------------------------------------------------------------- \n";

    public static final String URL_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label urlMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        urlMessage.setText(URL_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
