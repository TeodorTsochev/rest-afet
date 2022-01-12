package com.estafet.learning.steps;

import com.estafet.learning.utilities.Constants;
import com.estafet.learning.utilities.HelperMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ApiRequestsStepsDef {

    private static final Map<String, String> params = new HashMap<>();
    private static String requestUrl;
    private static Response response;

    @Given("^The API request address is set to (.*)$")
    public void apiUrlIsSet(String url) {
        requestUrl = url;
    }

    @Given("^Assign API key request parameter (.*) with value (.*)$")
    public void apiKeyRequestIsAssigned(String parameter, String key) {
        params.put(parameter, key);
    }

    @When("We make GET request to the API")
    public void makeGetRequest() {
        response = HelperMethods.doGet(params, requestUrl);
    }

    @Then("^The following is returned: response '(.*)', message '(.*)' and response code '(.*)' is returned$")
    public void specificMessageAndCodeIsReturned(String responseMsg, String errorMsg, int responseCode) {
        Assertions.assertEquals(responseCode, response.getStatusCode());
        Assertions.assertEquals(responseMsg, response.jsonPath().get(Constants.API_RESPONSE_FIELD));
        Assertions.assertEquals(errorMsg, response.jsonPath().get(Constants.API_ERROR_FIELD));
    }
}
