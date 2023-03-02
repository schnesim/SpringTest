package de.allianz.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SomeTest {

    @ParameterizedTest
    @CsvSource({"one, one_one", "two, two_two"})
    void testSomething(String status1, String status2) {
        System.out.println(status1 + ", " + status2);
    }
}
