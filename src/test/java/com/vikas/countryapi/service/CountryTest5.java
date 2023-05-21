package com.vikas.countryapi.service;

import com.vikas.countryapi.dao.CountryDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class CountryTest5 {

    private static CountryService countryService;
    private static final String env = "prod";

    @BeforeAll
    static void setup() {
        countryService = new CountryService(new CountryDao());
        System.out.println("Setup Complete");
    }

    @BeforeEach
    void beforeTest() {
        System.out.println("Before Test");
    }

    @Nested
    class CountryStartsWith{
        @Test
        void test_countryStartWith() {
            List<String> actual = countryService.getCountryStartsWith("I");
            Assertions.assertEquals(Arrays.asList("India", "Indonesia", "Italy"), actual);
        }

        @ParameterizedTest
        @ValueSource(strings = {"Z", "Y"})
        void test_null_FirstCountryStartsWith(String s) {
            String actual = countryService.getFirstCountryStartsWith(s);
            Assertions.assertNull(actual);
        }

        @ParameterizedTest
        @CsvSource({"I, 1","F, 2"})
        void test_NotNull_FirstCountryStartsWith_CSV(String s, String extra) {
            String actual = countryService.getFirstCountryStartsWith(s);
            Assertions.assertNotNull(actual);
        }

        @ParameterizedTest
        @ValueSource(strings = {"I", "F"})
        void test_NotNull_FirstCountryStartsWith(String s) {
            String actual = countryService.getFirstCountryStartsWith(s);
            Assertions.assertNotNull(actual);
        }

        @Test
        void test_getFirstCountryStartsWith_exception(){
            Executable executable = () -> countryService.getFirstCountryStartsWith(null);
            Assertions.assertThrows(Exception.class, executable);
        }

        @RepeatedTest(5)
        void test_getFirstCountryStartsWith_repeated(){
            Executable executable = () -> countryService.getFirstCountryStartsWith(null);
            Assertions.assertThrows(Exception.class, executable);
        }

        @Test
        void test_getFirstCountryStartsWith_assumption(){
            Assumptions.assumeTrue(CountryTest5.this.env.equals("stage"));
        }

        @Test
        void test_getFirstCountryStartsWith_performance(){
            Executable executable = () -> countryService.getFirstCountryStartsWith("I");
            Assertions.assertTimeout(Duration.ofMillis(100), executable);
        }
    }

    @Nested
    class OtherTests{

        @Test
        void test_countryContains() {
            boolean actual = countryService.containsCountries(Arrays.asList("India", "France"));
            Assertions.assertTrue(actual);
        }

        @Test
        @Disabled
        void disabledTest() {
            boolean actual = countryService.containsCountries(Arrays.asList("India", "France"));
            Assertions.assertTrue(actual);
        }

        @Test
        @DisabledOnOs(OS.WINDOWS)
        void disabledOnOsTest() {
            boolean actual = countryService.containsCountries(Arrays.asList("India", "France"));
            Assertions.assertTrue(actual);
        }
    }

    @AfterEach
    void afterTest() {
        System.out.println("After Test");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Cleanup Complete");
    }

}
