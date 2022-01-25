package com.getinspired.controller;

import com.getinspired.api_handler.API;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImageRequestController {

    /**
     * Rest controller that listens for HTTP GET requests on "/image" with a parameter "query".
     * Whenever a request is received, the controller will create a new API instance which processes the query.
     * Once the query is processed, the information is returned as a HTTP response, in form of a JSON object.
     * @param query - search keyword from user end.
     * @return Returns a JSON object as a HTTP request.
     * @throws JSONException - an error can occur whilst parsing the JSON.
     * @see API
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ResponseEntity<String> getImage(@RequestParam("query") String query) throws JSONException {
        JSONObject responseJSON = new JSONObject(new API(query).getImage());
        return ResponseEntity.ok()
                .body(responseJSON.toString());
    }
}
