package com.estafet.learning.utilities;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Collection of frequently used methods
 */
public class HelperMethods {

    /**
     * Method checking the expected request status code - no parameters
     *
     * @param url                - the exact URL
     * @param expectedStatusCode - status code expected
     */
    public static void doGetCheckStatusCode(String url, int expectedStatusCode) {
        given().when().urlEncodingEnabled(true).get(url).then().statusCode(expectedStatusCode);
    }

    /**
     * Method checking the expected request status code - parametrized
     *
     * @param params             - collection of request parameters
     * @param url                - the exact URL
     * @param expectedStatusCode - status code expected
     */
    public static void doGetCheckStatusCode(Map<String, String> params, String url, int expectedStatusCode) {
        given().queryParams(params).when().urlEncodingEnabled(true).get(url).then().statusCode(expectedStatusCode);
    }

    /**
     * Execute GET method
     *
     * @param params - collection of request parameters
     * @param url    - the exact URL
     * @return - Response from the GET request
     */
    public static Response doGet(Map<String, String> params, String url) {
        return given().queryParams(params).when().urlEncodingEnabled(true).get(url);
    }
}
