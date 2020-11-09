package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tag.Tag;

/**
 * A StatiticsData class that is used to facilitate the calculation of statistics used in the
 * time analysis feature.
 */
public class StatisticsData {
    private static final Logger logger = LogsCenter.getLogger(StatisticsData.class);
    private HashMap<Tag, Integer[]> stats;

    /**
     * Creates a statistics data object which can store information that summarises how much time is spent on
     * tasks and lessons for each module tag.
     */
    public StatisticsData() {
        logger.info("New StatisticsData object created");
        this.stats = new HashMap<>();
    }
    /**
     * Stores the module tag inside the data structure.
     */
    public void addTag(Tag tag) {
        requireNonNull(tag);
        logger.info("New tag added to StatisticsData object");
        Integer[] defaultValue = new Integer[]{0, 0};
        if (!stats.containsKey(tag)) {
            stats.put(tag, defaultValue);
        }
    }

    /**
     * Adds the time taken for tasks with the specified module tag.
     */
    public void addTaskTime(Tag tag, int value) {
        requireNonNull(tag);
        if (contains(tag)) {
            Integer[] current = stats.get(tag);
            Integer[] updated = new Integer[]{current[0] + value, current[1]};
            stats.put(tag, updated);
        }
    }
    /**
     * Adds the time taken for lessons with the specified module tag.
     */
    public void addLessonTime(Tag tag, int value) {
        requireNonNull(tag);
        if (contains(tag)) {
            Integer[] current = stats.get(tag);
            Integer[] updated = new Integer[]{current[0], current[1] + value};
            stats.put(tag, updated);
        }
    }
    /**
     * Returns true if the module tag is stored in the data structure.
     */
    public boolean contains(Tag tag) {
        requireNonNull(tag);
        return stats.containsKey(tag);
    }
    /**
     * Retrieves total time spent on tasks associated with module tag from data structure (in minutes)
     */
    public int getTotalTaskTime(Tag tag) {
        requireNonNull(tag);
        if (contains(tag)) {
            return stats.get(tag)[0];
        } else {
            return 0;
        }
    }
    /**
     * Retrieves total time spent on lessons associated with module tag from data structure (in minutes).
     */
    public int getTotalLessonTime(Tag tag) {
        requireNonNull(tag);
        if (contains(tag)) {
            return stats.get(tag)[1];
        } else {
            return 0;
        }
    }
    /**
     * Retrieves total time spent on both lessons and tasks associated with module tag from data structure (in minutes).
     */
    public int getTotalTime(Tag tag) {
        requireNonNull(tag);
        return getTotalTaskTime(tag) + getTotalLessonTime(tag);
    }
    public Set<Tag> getTags() {
        return stats.keySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mod | Task Time | Lesson Time | Total time \n");
        for (Tag tag : stats.keySet()) {
            sb.append(tag.tagName)
                .append(" | ")
                .append(getTotalTaskTime(tag))
                .append(" | ")
                .append(getTotalLessonTime(tag))
                .append(" | ")
                .append(getTotalTime(tag))
                .append("\n");
        }
        return sb.toString();
    }
}
