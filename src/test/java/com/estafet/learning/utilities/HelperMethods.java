package com.estafet.learning.utilities;

import io.restassured.response.Response;

import java.sql.*;
import java.util.Map;
import java.util.Properties;

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

    /**
     * Established a Returns the result set of a DB query
     *
     * @param dbUrl               - the exact url to the DB (e.g. "jdbc:postgresql://localhost:5432/postgres";)
     * @param dbQuery             - exact query which needs to be executed
     * @param dbRequestProperties - connection required properties like username, password, etc.
     * @return - result set of a DB query as ResultSet
     */
    public static ResultSet getQueryResultSet(String dbUrl, String dbQuery, Properties dbRequestProperties) {

        // Initialization of the result set variable
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(dbUrl, dbRequestProperties)) {

            // When this class first attempts to establish a connection,
            //      it automatically loads any JDBC 4.0 drivers found within
            //      the class path. Note that your application must manually
            //      load any JDBC drivers prior to version 4.0.
            Class.forName("org.postgresql.Driver");

            System.out.println("Connected to PostgreSQL database!");

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(dbQuery);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

        return resultSet;
    }
}
