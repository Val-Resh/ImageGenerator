package test;

import com.getinspired.Application;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIConnectionTest {
    private final String API_KEY = Application.API_KEY;
    private final String TEST = "https://api.pexels.com/v1/photos/2014422";

    /**
     * This is a method to test whether a connection can be established with the API.
     * It requires that a valid authorization key is provided which is stored in the API_KEY.txt
     * An HTTP client is created. Then, a request is formed. It takes the this.TEST as URL.
     * The TEST URL is provided by API for testing purpose. The type of request is a GET request.
     * It takes a header "Authorization" parameter which is the API_KEY. Then, this request is sent to API.
     * The response should be received and the status code checked.
     * @return - true if 200. False, otherwise.
     * @throws IOException - an error could occur with I/O operation. If this occurs, test is failed.
     * @throws InterruptedException - the thread could become interrupted. If this occurs, test is failed.
     */
    public boolean testConnection() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TEST))
                .GET()
                .header("Authorization", API_KEY)
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return (response.statusCode() == 200);
    }
}