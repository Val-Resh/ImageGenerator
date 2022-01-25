package com.getinspired.api_handler;

import com.getinspired.Application;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class API {
    private static final int MAX = 80;
    private JSONObject json;

    /**
     * Constructor to create an instance of an API interaction.
     * New instance should be generated per user query from the frontend.
     * @param query - a sentence keyword provided by user. Example: "cats"
     */
    public API(String query) {
        try {
            json = getJSON(query);
        } catch (IOException | JSONException | InterruptedException e) {
            json = null;
        }
    }

    /**
     * Private method that gets called whenever a new instance of API is created.
     * It takes the user query from the constructor. Then, creates an HTTP client with a GET request
     * and includes the query as a parameter for the API request. It receives a MAX number of images.
     * These images are an JSON object that is then returned, which is stored as an attribute for this API instance.
     * @param query -  a sentence keyword provided by user. It is passed from constructor.
     * @return JSON object that contains the various images and their information.
     * @throws IOException - an error could occur with I/O operation. If this occurs, test is failed.
     * @throws InterruptedException - the thread could become interrupted. If this occurs, test is failed.
     * @throws JSONException - potential error can occur when processing String to JSON object.
     */
    private JSONObject getJSON(String query) throws IOException, InterruptedException, JSONException {
        final String url = "https://api.pexels.com/v1/search?query=%s&per_page=%d"
                .formatted(URLEncoder.encode(query, StandardCharsets.UTF_8), MAX);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Authorization", Application.API_KEY)
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return new JSONObject(response.body());
    }

    /**
     * This method retrieves a random image and its information.
     * It uses the previously generated JSON image. The random value is between 0 and MAX.
     * The JSON object is trimmed down to the relevant information. This is then stored in a map structure.
     * @return HashMap that contains photographer name, their page url, and the image url.
     * @throws JSONException - an error could occur when parsing the JSON object.
     */
    public Map<String, String> getImage() throws JSONException {
        Map<String, String> map = new HashMap<>();
        int random = new Random().nextInt(MAX);
        JSONObject json = this.json.getJSONArray("photos").getJSONObject(random);
        JSONObject src = json.getJSONObject("src");
        map.put("photographer", json.getString("photographer"));
        map.put("photographer_url", json.getString("photographer_url"));
        map.put("image_url", src.getString("original"));
        return map;
    }
}
