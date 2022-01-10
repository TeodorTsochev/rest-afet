package com.estafet.learning;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Sanity checking "https://www.omdbapi.com/" API
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/APISmokeTest.feature"
        },
        plugin = {
                "pretty",
                "html:results/html/apiSmokeTest.html",
                "json:results/json/apiSmokeTest.json"
        },
        glue = {
                "com.estafet.learning.steps"
        },
        monochrome = true
)
public class ApiSmokeTest {
}
