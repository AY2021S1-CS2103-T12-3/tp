package seedu.address.ui.card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.StatisticsData;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;

public class StatsSummaryCard extends UiPart<Region> {
    private static final String FXML = "StatsSummaryCard.fxml";

    public final Tag tag;

    @FXML
    private Label moduleCode;
    @FXML
    private Label lessonTime;
    @FXML
    private Label taskTime;
    @FXML
    private Label totalTime;

    public StatsSummaryCard(Tag tag, StatisticsData dataSet) {
        super(FXML);
        this.tag = tag;
        moduleCode.setText(tag.tagName);
        lessonTime.setText("" + dataSet.getTotalLessonTime(tag));
        taskTime.setText("" + dataSet.getTotalTaskTime(tag));
        totalTime.setText("" + dataSet.getTotalTime(tag));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonCard)) {
            return false;
        }

        // state check
        StatsSummaryCard card = (StatsSummaryCard) other;
        return tag.equals(card.tag);
    }
}
