package com.estafet.learning;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiSanityChecksTest {

    @Test
    @DisplayName("Kick off test")
    void test_1() {
        Assertions.assertEquals("100", String.valueOf(100));
    }
}
