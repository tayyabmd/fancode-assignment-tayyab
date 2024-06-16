package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    /**
     * Fetches a list of users from the API endpoint `/users`.
     *
     * @return List<Map<String, Object>> - List of users, where each user is represented as a map of key-value pairs.
     */
    public List<Map<String, Object>> fetchUsers() {
        Response response = getRequest("/users");
        return response.jsonPath().getList("$");
    }

    /**
     * Fetches a list of todos from the API endpoint `/todos`.
     *
     * @return List<Map<String, Object>> - List of todos, where each todo is represented as a map of key-value pairs.
     */
    public List<Map<String, Object>> fetchTodos() {
        Response response = getRequest("/todos");
        return response.jsonPath().getList("$");
    }

    /**
     * Filters the list of all users to return only those who belong to the city "FanCode".
     * Uses geographical coordinates (latitude and longitude) to determine if a user's address
     * falls within predefined boundaries for FanCode city.
     *
     * @return List<Map<String, Object>> - Filtered list of users who belong to the "FanCode" city,
     *         each represented as a map of key-value pairs.
     */
    public List<Map<String, Object>> fetchUsersInFanCodeCity() {
        List<Map<String, Object>> allUsers = fetchUsers();
        return allUsers.stream()
                .filter(user -> {
                    Map<String, Object> address = (Map<String, Object>) user.get("address");
                    Map<String, Object> geo = (Map<String, Object>) address.get("geo");
                    double lat = Double.parseDouble(geo.get("lat").toString());
                    double lng = Double.parseDouble(geo.get("lng").toString());
                    return isUserInFanCodeCity(lat, lng);
                })
                .collect(Collectors.toList());
    }

    /**
     * Checks if a user's geographical coordinates (latitude and longitude) fall within the
     * predefined boundaries for the "FanCode" city.
     *
     * @param lat Latitude of the user's location.
     * @param lng Longitude of the user's location.
     * @return boolean - True if the user belongs to the "FanCode" city, false otherwise.
     */
    private boolean isUserInFanCodeCity(double lat, double lng) {
        // Define the geographical boundaries for FanCode city
        double latMin = -40;
        double latMax = 5;
        double lngMin = 5;
        double lngMax = 100;
        // Check if the user's coordinates fall within FanCode city boundaries
        return lat >= latMin && lat <= latMax && lng >= lngMin && lng <= lngMax;
    }

    /**
     * Executes a GET request to the specified API path and returns the response.
     *
     * @param path API endpoint path to send the GET request.
     * @return Response - RestAssured Response object containing the response details.
     */
    private Response getRequest(String path) {
        RequestSpecification requestSpec = RestAssured.given().baseUri(BASE_URI);
        return requestSpec.get(path);
    }
}
