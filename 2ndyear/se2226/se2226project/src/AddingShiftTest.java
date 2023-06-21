import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AddingShiftTest extends AutomatedTest {
    static final String testWorkstationName = "AddingShiftTestWorkstation";

    //Some of the test cases are disabled because the website's functionality changed since these were written. (there is now only the constraint of from being earlier than to time, no more the 08:00 lower bound)
    private static Stream<Arguments> fromToPairs() {
        return Stream.of(
//                Arguments.of("08:00", "08:01", true),
//                Arguments.of("08:00", "08:02", true),
//                Arguments.of("08:00", "15:00", true),
//                Arguments.of("08:00", "23:58", true),
//                Arguments.of("08:00", "23:59", true),
//                Arguments.of("08:01", "08:02", true),
//                Arguments.of("08:01", "08:03", true),
//                Arguments.of("08:01", "17:00", true),
//                Arguments.of("08:01", "23:58", true),
//                Arguments.of("08:01", "23:59", true),
                Arguments.of("12:00", "12:01", true),
                Arguments.of("12:00", "12:02", true),
                Arguments.of("12:00", "19:00", true),
                Arguments.of("12:00", "23:58", true),
                Arguments.of("12:00", "23:59", true),
                Arguments.of("23:57", "23:58", true),
                Arguments.of("23:57", "23:59", true),
                Arguments.of("23:58", "23:59", true),
//                Arguments.of("08:00", "07:59", false),
//                Arguments.of("08:00", "08:00", false),
//                Arguments.of("08:01", "08:00", false),
//                Arguments.of("08:01", "08:01", false),
                Arguments.of("14:00", "13:59", false),
                Arguments.of("14:00", "14:00", false),
                Arguments.of("23:57", "23:56", false),
                Arguments.of("23:57", "23:57", false),
                Arguments.of("23:58", "23:57", false),
                Arguments.of("23:58", "23:58", false),
//                Arguments.of("08:00", "00:00", false),
//                Arguments.of("08:01", "00:00", false),
                Arguments.of("19:00", "00:00", false),
                Arguments.of("23:57", "00:00", false),
                Arguments.of("23:58", "00:00", false),
//                Arguments.of("00:00", "00:01", false),
//                Arguments.of("00:00", "00:02", false),
//                Arguments.of("00:00", "17:00", false),
//                Arguments.of("00:00", "23:58", false),
//                Arguments.of("00:00", "23:59", false),
//                Arguments.of("07:59", "08:00", false),
//                Arguments.of("07:59", "08:01", false),
//                Arguments.of("07:59", "20:00", false),
//                Arguments.of("07:59", "23:58", false),
//                Arguments.of("07:59", "23:59", false),
//                Arguments.of("00:00", "23:59", false),
                Arguments.of("00:00", "00:00", false)
//                Arguments.of("07:59", "07:58", false),
//                Arguments.of("07:59", "07:59", false),
//                Arguments.of("07:59", "00:00", false)
        );
    }

    @BeforeAll
    static void addTestWorkstation() {
        seleniumApsUtil.addWorkstation(testWorkstationName);
        seleniumApsUtil.goToTab("workingHours", testWorkstationName);
    }

    @ParameterizedTest
    @MethodSource("fromToPairs")
    void testAddingShift(String from, String to, boolean expected) {
        boolean result = seleniumApsUtil.addMondayShiftToCurrentWorkstation(from, to);
        if(result) {
            seleniumApsUtil.deleteFirstMondayShiftOfCurrentWorkstation();
        }
        assertEquals(expected, result);
    }

    @AfterAll
    static void deleteTestWorkstation() {
        seleniumApsUtil.deleteWorkstation(testWorkstationName);
    }
}