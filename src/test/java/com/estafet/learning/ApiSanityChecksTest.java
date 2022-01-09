package com.estafet.learning;

import com.estafet.learning.utilities.Constants;
import com.estafet.learning.utilities.HelperMethods;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Sanity checking "https://api.binance.com" API
 */
public class ApiSanityChecksTest {

    Logger LOGGER = LogManager.getLogger(ApiSanityChecksTest.class);

    @Test
    @DisplayName("Sanity check that API is actually UP")
    void apiSanityCheck() {
        HelperMethods.doGetCheckStatusCode(Constants.URL, HttpStatus.SC_OK);
    }


}
