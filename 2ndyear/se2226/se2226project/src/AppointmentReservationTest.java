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
    final int SPINNER_WAIT_TIME_S = 10;
    final String testWorkstationName = "AppointmentReservationTestWorkstation";

    void goToWorkstationsList() {
        driver.findElement(By.id("sidebar_item_locations")).click();
        driver.findElement(By.xpath("((//table[1]/tbody/tr)[1])//td[2]//a")).click();
        driver.findElement(By.id("stations-tab")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void selectLastTab(String tabsDivID) {
        driver.findElement(By.xpath("//div[@id='" + tabsDivID + "']//ul//li[last()]//a")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    String getSelectedPillXPath(String parentID) {
        return "//div[@id='" + parentID + "']//div[@id='pills-tabContent']//div[@class='tab-pane fade active show']";
    }

    void waitForSpinnerToDisappear() {
        var wait = new WebDriverWait(driver, Duration.ZERO.plusSeconds(SPINNER_WAIT_TIME_S));
        Function<WebDriver, Boolean> spinnerNotDisplayed = driver -> !driver.findElement(By.id("spinnerModal")).isDisplayed();
        wait.until(spinnerNotDisplayed);
    }

    void waitUntilExistent(String elementId, int wait_s) {
        var wait = new WebDriverWait(driver, Duration.ZERO.plusSeconds(wait_s));
        Function<WebDriver, Boolean> reserveButtonExistent = driver -> {
            try {
                driver.findElement(By.id(elementId));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        };
        wait.until(reserveButtonExistent);
    }

    void goToAvailableTimes() {
        driver.findElement(By.id("sidebar_item_selfBooking")).click();
        //pick first category
        driver.findElement(By.xpath("//div[contains(@class, 'clickable-card')][1]")).click();
        //pick first service
        driver.findElement(By.xpath("//div[contains(@class, 'clickable-card')][1]")).click();
        driver.findElement(By.id("i_understand_button")).click();
        //pick last workstation
        driver.findElement(By.xpath("//div[contains(@class, 'clickable-card')][last()]")).click();

        waitForSpinnerToDisappear();
        try {
            driver.findElement(By.xpath("//div[contains(@class, 'clickable-card')][last()]")).click();
            waitForSpinnerToDisappear();
        } catch(org.openqa.selenium.ElementClickInterceptedException e) {
            System.out.println("The available times api fetch took longer than " + SPINNER_WAIT_TIME_S + " seconds (elements intractable)");
            throw e;
        }
    }

    //The point of making this a test is more to ensure the order of execution rather than testing the functionality (but it is, coincidentally, to some extent testing it).
    @Test
    @Order(1)
    void addTestWorkstation() {
        goToWorkstationsList();
        driver.findElement(By.id("addWorkstationButton")).click();
        var nameTextBoxElement = driver.findElement(By.id("name"));
        nameTextBoxElement.click();
        nameTextBoxElement.sendKeys(testWorkstationName);
        driver.findElement(By.id("addWorkstationSubmitButton")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("workingHours-tab")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        selectLastTab("workingHours");
        driver.findElement(By.xpath(getSelectedPillXPath("workingHours") + "//div[@class='workingHours'][1]")).click();
        driver.findElement(By.id("addWorkingTimeSubmitButton")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("services-tab")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        selectLastTab("services");
        driver.findElement(By.xpath(getSelectedPillXPath("services") + "//a[contains(@class, 'btn')]")).click();
        driver.findElement(By.xpath("//input[@class='form-check-input'][1]")).click();
        driver.findElement(By.id("saveButton")).click();
        assertTrue(true);
    }

    void clickAvailableTime(String time) {
        driver.findElement(By.xpath(String.format("//button[contains(text(), '%s')]", time))).click();
    }

    @ParameterizedTest
    @ValueSource(strings = {"9:00", "9:20", "14:00", "17:20", "17:40"}) //BVA
    @Order(2)
    public void testAppointmentReservation(String time) {
        driver.get("http://76.72.163.151:8060/company/show");
        goToAvailableTimes();
        clickAvailableTime(time);
        waitUntilExistent("reserveButton", 20);
        driver.findElement(By.id("reserveButton")).click();
        var nameTextBox = driver.findElement(By.id("person_1_name"));
        nameTextBox.click();
        nameTextBox.sendKeys("John");
        var surnameTextBox = driver.findElement(By.id("person_1_surname"));
        surnameTextBox.click();
        surnameTextBox.sendKeys("Doe");
        driver.findElement(By.id("completeReservationButton")).click();
        try {
            waitUntilExistent("check_icon_div", 10);
        } catch(org.openqa.selenium.TimeoutException e) {
            fail();
        }
        assertTrue(true);
    }

    //The point of making this a test is more to ensure the order of execution rather than testing the functionality (but it is, coincidentally, to some extent testing it).
    @Test
    @Order(3)
    void deleteTestWorkstation() {
        driver.get("http://76.72.163.151:8060/company/show");
        goToWorkstationsList();
        String lastTDXpath = "//div[@id='stations']//table//tbody//tr[last()]//td[last()]";
        driver.findElement(By.xpath(lastTDXpath + "//a")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//div[@class='dropdown-menu show']//a[last()]")).click();
        assertTrue(true);
    }
}