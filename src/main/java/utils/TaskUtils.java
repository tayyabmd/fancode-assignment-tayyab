package utils;

import java.util.List;
import java.util.Map;

public class TaskUtils {

    /**
     * Calculates the total number of todos for a specific user.
     *
     * @param todos  List of todos where each todo is represented as a Map<String, Object>.
     * @param userId ID of the user for whom todos are to be counted.
     * @return long - Total number of todos for the specified user ID.
     */
    public static long getTotalTodosForUser(List<Map<String, Object>> todos, int userId) {
        return todos.stream()
                .filter(todo -> todo.get("userId").equals(userId))
                .count();
    }

    /**
     * Calculates the number of completed todos for a specific user.
     *
     * @param todos  List of todos where each todo is represented as a Map<String, Object>.
     * @param userId ID of the user for whom completed todos are to be counted.
     * @return long - Number of completed todos for the specified user ID.
     */
    public static long getCompletedTodosForUser(List<Map<String, Object>> todos, int userId) {
        return todos.stream()
                .filter(todo -> todo.get("userId").equals(userId) && (boolean) todo.get("completed"))
                .count();
    }
}
