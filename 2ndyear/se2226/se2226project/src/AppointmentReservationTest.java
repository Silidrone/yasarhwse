import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
        driver.get("http://76.72.163.151:8060/company/show");
        assertTrue(seleniumApsUtil.reserveAppointment(testWorkstationName, time, "Adam", "Smith"));
    }

    @AfterAll
    static void deleteTestWorkstation() {
        driver.get("http://76.72.163.151:8060/company/show");
        seleniumApsUtil.deleteWorkstation(testWorkstationName);
    }
}