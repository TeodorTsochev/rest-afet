package com.estafet.learning.steps;

import com.estafet.learning.utilities.Constants;
import com.estafet.learning.utilities.HelperMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBRequestsStepsDef {

    private static final Logger LOGGER = LogManager.getLogger(HelperMethods.class);

    private static final Properties requestProperties = new Properties();

    private static String query;

    private static String requestUrl;

    private static boolean isConnected;

    @Given("^The DB request address is set to (.*)$")
    public void apiUrlIsSet(String url) {
        requestUrl = url;
    }

    @Given("^The username and password are set to (.*) and (.*)$")
    public void setUsernameAndPass(String username, String password) {
        requestProperties.setProperty("user", username);
        requestProperties.setProperty("password", password);
    }

    @Given("^The table of interest is (.*)")
    public void setExactTableInQuery(String tableName) {
          query = Constants.SELECT_ALL_STATEMENT_TEMPLATE.
                  concat(Constants.DEFAULT_SCHEMA).concat(Constants.DOT_SYMBOL).concat(Constants.TABLE_CUSTOMERS);
    }

    @When("Connection is attempted")
    public void attemptConnection() {
        isConnected = HelperMethods.isConnectionSuccessful(requestUrl, requestProperties);
    }

    @Then("^Connection is established (.*)$")
    public void connectionEstablished(boolean connection) {
        Assertions.assertEquals(connection, isConnected);
    }

    @Then("The number of columns returned is {int}")
    public void theNumberOfColumnsReturnedIs(int expectedColumnCount) {
        ResultSet resultSet = HelperMethods.getQueryResultSet(requestUrl, query, requestProperties);
        int actualColumnCount = 0;

        if (resultSet != null) {
            try {
                actualColumnCount = resultSet.getMetaData().getColumnCount();
            } catch (SQLException e) {
                LOGGER.error(Constants.CONNECTION_ERROR_MESSAGE, e);
            }
        }

        Assertions.assertEquals(expectedColumnCount, actualColumnCount);
    }

    @Then("Column contents are NOT NULL")
    public void columnContentsNotNullCheck() {
        ResultSet resultSet = HelperMethods.getQueryResultSet(requestUrl, query, requestProperties);

        try {
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {

                    String columnContent = resultSet.getString(i);
                    String[] exactRecord = columnContent.split(StringUtils.LF);

                    for (String record : exactRecord) {
                        Assertions.assertFalse(HelperMethods.isNullOrEmpty(record),
                                String.format("Failed Assertion: column (%s) >>> record (%s)",
                                        resultSet.getMetaData().getColumnName(i), record));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Constants.CONNECTION_ERROR_MESSAGE, e);
        }
    }
}
