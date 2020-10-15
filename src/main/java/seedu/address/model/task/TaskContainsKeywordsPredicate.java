package seedu.address.model.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Task}'s given attribute matches any of the keywords given.
 */
public class TaskContainsKeywordsPredicate implements Predicate<Task> {
    private final Map<Prefix, List<String>> keywords;

    public TaskContainsKeywordsPredicate() {
        this.keywords = new HashMap<>();
    }

    public void setKeyword(Prefix attribute, String keyword) {
        List<String> extendedKeywords = new ArrayList<>();
        if (keywords.containsKey(attribute)) {
            extendedKeywords = keywords.get(attribute);
        }
        extendedKeywords.add(keyword);
        keywords.put(attribute, extendedKeywords);
    }

    @Override
    public boolean test(Task task) {
        for (Map.Entry<Prefix, List<String>> entry : keywords.entrySet()) {
            Prefix prefix = entry.getKey();
            List<String> words = entry.getValue();
            if (isMatched(prefix.getPrefix(), words, task)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskContainsKeywordsPredicate)) {
            return false;
        }

        TaskContainsKeywordsPredicate casted = (TaskContainsKeywordsPredicate) other;
        for (Prefix key : keywords.keySet()) {
            if (!casted.keywords.containsKey(key) || !keywords.get(key).equals(casted.keywords.get(key))) {
                return false;
            }
        }
        return true;
    }

    private boolean isMatched(String prefix, List<String> words, Task task) {
        if (prefix.equals("title:")) {
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(task.getTitle().title, keyword));
        }

        if (prefix.equals("desc:")) {
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(task.getDescription().value, keyword));
        }

        if (prefix.equals("date:")) {
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(task.getDateTime().value, keyword));
        }

        if (prefix.equals("type:")) {
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(task.getType().value, keyword));
        }
        if (prefix.equals("status:")) {
            // must be an exact match here
            return words.stream()
                    .anyMatch(keyword -> task.getStatus().value.toString().toLowerCase().equals(keyword.toLowerCase()));
        }

        return false;
    }


}
