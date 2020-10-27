package seedu.address.storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Planus;
import seedu.address.model.StatisticsData;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;


/**
 * This class handles all computation related to time spent on tasks and lessons.
 */
public class Statistics {
    private static final Logger logger = LogsCenter.getLogger(JsonPlanusStorage.class);
    private static final Path filePath = Paths.get("data", "planus.json");

    /**
     * Computes total duration of time spent on all tasks and lessons for the specified time period and
     * stores it in a data structure.
     */
    public static StatisticsData generateStatistics(LocalDate startDate, LocalDate endDate) {
        Planus planus = null;
        StatisticsData stats = new StatisticsData();
        try {
            JsonSerializablePlanus dataFromFile = JsonUtil.readJsonFile(filePath,
                    JsonSerializablePlanus.class).get();
            planus = dataFromFile.toModelType();
            System.out.println(planus);
        } catch (Exception e) {
            logger.info("Failed to read PlaNus storage file in " + filePath + ": " + e.getMessage());
        }
        if (planus == null) {
            logger.info("Unable to get statistics summary as storage file is not found.");
            return null;
        }
        logger.info("Generating summary for statistics.");
        //TODO: update this after implementing filtering
        updateTimeTakenForTasks(planus.getTaskList(), stats, startDate, endDate);
        updateTimeTakenForLessons(planus.getLessonList(), stats, startDate, endDate);
        logger.info("Generated statistics \n" + stats.toString());
        System.out.println(stats.toString());
        return stats;
    }
    private static FilteredList<Task> filterTasksWithinPeriod(ObservableList<Task> tasks, LocalDate start,
                                                              LocalDate end) {
        return tasks.filtered(task -> {
            LocalDate taskDate = task.getDate();
            return (taskDate.isEqual(start) || taskDate.isAfter(start))
                && (taskDate.isBefore(end) || taskDate.isEqual(end));
        });
    }
    
    private static void updateTimeTakenForTasks(ObservableList<Task> tasks, StatisticsData stats,
                                               LocalDate startDate, LocalDate endDate) {
        FilteredList<Task> filteredTasks = filterTasksWithinPeriod(tasks, startDate, endDate);
        for (Task task: filteredTasks) {
            Tag currentTag = task.getTag();
            if (!stats.contains(currentTag)) {
                stats.addTag(currentTag);
            }
            stats.addTaskTime(currentTag, task.getTimeTaken());
        }
    }

    private static void updateTimeTakenForLessons(ObservableList<Lesson> lessons, StatisticsData stats,
                                                 LocalDate startDate, LocalDate endDate) {
        for (Lesson lesson: lessons) {
            Tag currentTag = lesson.getTag();
            if (!stats.contains(currentTag)) {
                stats.addTag(currentTag);
            }
            stats.addLessonTime(currentTag, lesson.timeTakenWithinPeriod(startDate, endDate));
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        //test
        try {
            generateStatistics(ParserUtil.parseDate("01-05-2020"),
                    ParserUtil.parseDate("01-05-2020"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
