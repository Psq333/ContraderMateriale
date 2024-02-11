package it.contrader.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a response from the application. It encapsulates all the details of a response,
 * including response data.
 */
public class Response {
    private Map<String, Object> body;

    /**
     * Default constructor that initializes an empty body for the response.
     */
    public Response() {
        body = new HashMap<>();
    }

    /**
     * Constructs a response with the given body.
     *
     * @param body the response data.
     */
    public Response(Map<String, Object> body) {
        this.body = body;
    }

    /**
     * Returns the body of the response, which contains the response data.
     *
     * @return the body of the response.
     */
    public Map<String, Object> getBody() {
        return body;
    }

    /**
     * Sets the body of the response.
     *
     * @param body the body of the response.
     */
    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    /**
     * Adds an item to the body of the response.
     *
     * @param key the key of the item to be added.
     * @param value the value of the item to be added.
     */
    public void put(String key, Object value) {
        this.body.put(key, value);
    }
}

