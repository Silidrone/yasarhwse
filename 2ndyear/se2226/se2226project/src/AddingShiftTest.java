import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AddingShiftTest extends AutomatedTest {
    static final String testWorkstationName = "AddingShiftTestWorkstation";

    private static Stream<Arguments> fromToPairs() {
        return Stream.of(
                new String[]{"08:00", "08:01"},
                new String[]{"08:00", "08:02"},
                new String[]{"08:00", "15:00"},
                new String[]{"08:00", "23:58"},
                new String[]{"08:00", "23:59"},
                new String[]{"08:01", "08:02"},
                new String[]{"08:01", "08:03"},
                new String[]{"08:01", "17:00"},
                new String[]{"08:01", "23:58"},
                new String[]{"08:01", "23:59"},
                new String[]{"12:00", "12:01"},
                new String[]{"12:00", "12:02"},
                new String[]{"12:00", "19:00"},
                new String[]{"12:00", "23:58"},
                new String[]{"12:00", "23:59"},
                new String[]{"23:57", "23:58"},
                new String[]{"23:57", "23:59"},
                new String[]{"23:58", "23:59"},
                new String[]{"08:00", "07:59"},
                new String[]{"08:00", "08:00"},
                new String[]{"08:01", "08:00"},
                new String[]{"08:01", "08:01"},
                new String[]{"14:00", "13:59"},
                new String[]{"14:00", "14:00"},
                new String[]{"23:57", "23:56"},
                new String[]{"23:57", "23:57"},
                new String[]{"23:58", "23:57"},
                new String[]{"23:58", "23:58"},
                new String[]{"08:00", "00:00"},
                new String[]{"08:01", "00:00"},
                new String[]{"19:00", "00:00"},
                new String[]{"23:57", "00:00"},
                new String[]{"23:58", "00:00"},
                new String[]{"00:00", "00:01"},
                new String[]{"00:00", "00:02"},
                new String[]{"00:00", "17:00"},
                new String[]{"00:00", "23:58"},
                new String[]{"00:00", "23:59"},
                new String[]{"07:59", "08:00"},
                new String[]{"07:59", "08:01"},
                new String[]{"07:59", "20:00"},
                new String[]{"07:59", "23:58"},
                new String[]{"07:59", "23:59"},
                new String[]{"00:00", "23:59"},
                new String[]{"00:00", "00:00"},
                new String[]{"07:59", "07:58"},
                new String[]{"07:59", "07:59"},
                new String[]{"00:00", "00:00"},
                new String[]{"07:59", "00:00"}
        ).map(pair -> Arguments.of(pair[0], pair[1]));
    }

    //The point of making this a test is more to ensure the order of execution rather than testing the functionality (but it is, coincidentally, to some extent testing it).
    @BeforeAll
    static void addTestWorkstation() {
        driver.get("http://76.72.163.151:8060/company/show");
        seleniumApsUtil.addWorkstation(testWorkstationName);
        assertTrue(true);
    }

    @ParameterizedTest
    @MethodSource("fromToPairs")
    void testAddingShift(String from, String to) {
        seleniumApsUtil.goToFirstLocationDetails();
        assertTrue(seleniumApsUtil.addMondayShift(testWorkstationName, from, to));
    }

    @AfterAll
    static void deleteTestWorkstation() {
        seleniumApsUtil.deleteWorkstation(testWorkstationName);
        assertTrue(true);
    }
}