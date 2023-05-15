import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AddingWorkingExceptionTest extends AutomatedTest {
    static final String testWorkstationName = "AddingWorkingExceptionTestWorkstation";
    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final LocalDateTime today = LocalDateTime.now();

    private static Stream<Arguments> inputVariablesPairs() {
        return Stream.of(
                Arguments.of(today.plusDays(1), false, true),
                Arguments.of(today.plusDays(2), false, true),
                Arguments.of(today.plusDays(10), false, true),
                Arguments.of(today.plusDays(364), false, true),
                Arguments.of(today.plusDays(365), false, true),
                Arguments.of(today.plusDays(1), true, false),
                Arguments.of(today.plusDays(2), true, false),
                Arguments.of(today.plusDays(10), true, false),
                Arguments.of(today.plusDays(364), true, false),
                Arguments.of(today.plusDays(365), true, false),
                Arguments.of(today, false, false),
                Arguments.of(today.minusDays(1), false, false),
                Arguments.of(today, true, false),
                Arguments.of(today.minusDays(1), true, false),
                Arguments.of(today.plusDays(366), false, false),
                Arguments.of(today.plusDays(367), false, false),
                Arguments.of(today.plusDays(366), true, false),
                Arguments.of(today.plusDays(367), true, false)
        );
    }

    @BeforeAll
    static void addTestWorkstation() {
        seleniumApsUtil.addWorkstation(testWorkstationName);
        seleniumApsUtil.goToTab("workingExceptions", testWorkstationName);
    }

    @ParameterizedTest
    @MethodSource("inputVariablesPairs")
    void testAddingWorkingException(LocalDateTime ldt, boolean alreadyChosen, boolean expected) {
        boolean result = seleniumApsUtil.addWorkingExceptionToCurrentWorkstation(dtf.format(ldt));
        boolean secondResult = true;
        if(alreadyChosen) {
            secondResult = seleniumApsUtil.addWorkingExceptionToCurrentWorkstation(dtf.format(ldt));
            if(secondResult) {
                seleniumApsUtil.deleteFirstWorkingExceptionOfCurrentWorkstation();
            }
        }
        if(result) {
            seleniumApsUtil.deleteFirstWorkingExceptionOfCurrentWorkstation();
        }
        assertEquals(result && secondResult, expected);
    }

    @AfterAll
    static void deleteTestWorkstation() {
        seleniumApsUtil.deleteWorkstation(testWorkstationName);
    }
}