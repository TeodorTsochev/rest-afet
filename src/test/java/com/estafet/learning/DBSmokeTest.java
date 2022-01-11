package com.estafet.learning;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Sanity checking records in local postgre DB table "Customers"
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/DBSmokeTest.feature"
        },
        plugin = {
                "pretty",
                "html:results/html/dbSmokeTest.html",
                "json:results/json/dbSmokeTest.json"
        },
        glue = {
                "com.estafet.learning.steps"
        },
        monochrome = true
)
public class DBSmokeTest {
}
