import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppointmentReservationTest extends AutomatedTest {
    static final String testWorkstationName = "AppointmentReservationTestWorkstation";

    @BeforeAll
    static void addTestWorkstation() {
        seleniumApsUtil.addWorkstation(testWorkstationName);
        seleniumApsUtil.goToTab("workingHours", testWorkstationName);
        seleniumApsUtil.addMondayShiftToCurrentWorkstation();
        seleniumApsUtil.assignWorkstationToFirstService(testWorkstationName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"9:00", "9:20", "14:00", "17:20", "17:40"}) //BVA
    public void testAppointmentReservation(String time) {
        driver.get(String.format("https://%s/company/show", baseURL));
        assertTrue(seleniumApsUtil.reserveAppointment(testWorkstationName, time, "Adam", "Smith"));
    }

    @AfterAll
    static void deleteTestWorkstation() {
        driver.get(String.format("https://%s/company/show", baseURL));
        seleniumApsUtil.deleteWorkstation(testWorkstationName);
    }
}