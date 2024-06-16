package stepdefs;

import api.UserService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.TaskUtils;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class FancodeStepDefinitions {

    private UserService userService = new UserService();
    private List<Map<String, Object>> users;
    private List<Map<String, Object>> todos;

    /**
     * Fetches the todo tasks from the API and stores them in the `todos` list.
     */
    @Given("User has the todo tasks")
    public void userHasTodoTasks() {
        todos = userService.fetchTodos();
        System.out.println("Number of todos fetched: " + todos.size());
    }

    /**
     * Filters users by the city FanCode and stores them in the `users` list.
     */
    @And("User belongs to the city FanCode")
    public void filterUsersByCityFanCode() {
        users = userService.fetchUsersInFanCodeCity();
        System.out.println("Number of users in FanCode city: " + users.size());
    }

    /**
     * Verifies that each user in the city FanCode has more than the specified percentage of completed tasks.
     *
     * @param percentageThreshold the minimum percentage of completed tasks required.
     */
    @Then("User Completed task percentage should be greater than {int}%")
    public void verifyCompletedTasksPercentage(int percentageThreshold) {
        assertTrue("Users list should not be empty", users != null && !users.isEmpty());
        assertTrue("Todos list should not be empty", todos != null && !todos.isEmpty());

        users.forEach(user -> {
            int userId = (int) user.get("id");
            long totalTodos = TaskUtils.getTotalTodosForUser(todos, userId);
            long completedTodos = TaskUtils.getCompletedTodosForUser(todos, userId);
            double completedPercentage = (completedTodos * 100.0) / totalTodos;

            System.out.println("User " + user.get("username") + " has completed " + completedPercentage + "% of their tasks.");
            assertTrue("Completed percentage should be greater than " + percentageThreshold + "%", completedPercentage > percentageThreshold);
        });
    }
}
