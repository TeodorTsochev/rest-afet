package com.estafet.learning.utilities;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

/**
 * Collection of frequently used methods
 */
public class HelperMethods {

    private static final Logger LOGGER = LogManager.getLogger(HelperMethods.class);

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
     * Establish a connection to DB and return the result set of a DB query
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
            // Load postgre db driver
            Class.forName(Constants.POSTGRE_DRIVER_PACKAGE);

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(dbQuery);
        } catch (Exception e) {
            LOGGER.error(Constants.CONNECTION_ERROR_MESSAGE, e);
        }

        return resultSet;
    }

    /**
     * Establish a connection to DB and return the result set of a DB query
     *
     * @param dbUrl               - the exact url to the DB (e.g. "jdbc:postgresql://localhost:5432/postgres";)
     * @param dbRequestProperties - connection required properties like username, password, etc.
     * @return - true/false if connection is established
     */
    public static boolean isConnectionSuccessful(String dbUrl, Properties dbRequestProperties) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbRequestProperties)) {
            // Load postgre db driver
            Class.forName(Constants.POSTGRE_DRIVER_PACKAGE);
            
            return true;
        } catch (Exception e) {
            LOGGER.error("Connection refused using illegitimate credentials (user={} / pass={})",
                    dbRequestProperties.getProperty(Constants.USERNAME_PROPERTY),
                    dbRequestProperties.getProperty(Constants.PASSWORD_PROPERTY));
            return false;
        }
    }

    /**
     * Boolean checker whether a provided string value is either null or empty
     *
     * @param str - string value provided
     * @return - true / false
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
