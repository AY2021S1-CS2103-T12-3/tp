package seedu.address.ui.panel;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;
import seedu.address.ui.UiPart;
import seedu.address.ui.card.CalendarDayEventCard;


public class CalendarDayPanel extends UiPart<Region> {
    private static final String FXML = "panel/CalendarDayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarDayEventCard.class);

    @FXML
    private ListView<Task> scheduleView;
    @FXML
    private Label date;

    /**
     * Create a CalendarDayPanel object to hold day event information.
     * @param schedule the filtered tasks that will be displayed in the calendar view
     * @param dateTime the date time of the day cell in the calendar view
     */
    public CalendarDayPanel(ObservableList<Task> schedule, LocalDateTime dateTime) {
        super(FXML);
        displayDate(dateTime);
        scheduleView.setItems(schedule);
        scheduleView.setCellFactory(listView -> new CalendarDayPanelCell());
    }

    private void displayDate(LocalDateTime dateTime) {
        date.setText(dateTime.getMonth().toString() + " " + dateTime.getDayOfMonth());
    }

    class CalendarDayPanelCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CalendarDayEventCard(task).getRoot());
            }
        }
    }
}
