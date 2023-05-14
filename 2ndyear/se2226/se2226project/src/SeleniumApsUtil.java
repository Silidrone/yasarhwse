import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.fail;

public class SeleniumApsUtil {
    final int SPINNER_WAIT_TIME_S = 10;
    final int JS_TAB_LOAD_TIME_MS = 500;
    protected WebDriver driver;

    SeleniumApsUtil(WebDriver driver) {
        this.driver = driver;
    }

    String getSelectedChildPillXPath() {
        return "//div[@class='tab-pane fade active show']//div[@id='pills-tabContent']//div[@class='tab-pane fade active show']";
    }

    void waitForJSTabLoad() {
        try {
            Thread.sleep(JS_TAB_LOAD_TIME_MS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void waitForSpinnerToDisappear() {
        var wait = new WebDriverWait(driver, Duration.ZERO.plusSeconds(SPINNER_WAIT_TIME_S));
        Function<WebDriver, Boolean> spinnerNotDisplayed = driver -> !driver.findElement(By.id("spinnerModal")).isDisplayed();
        wait.until(spinnerNotDisplayed);
    }

    boolean elementExists(By by, WebDriver driver) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    boolean elementExists(By by) {
        return elementExists(by, driver);
    }

    boolean elementExists(String elementID, WebDriver driver) {
        return elementExists(By.id(elementID), driver);
    }

    boolean elementExists(String elementID) {
        return elementExists(elementID, driver);
    }

    void waitUntilExistent(String elementID, int wait_s) {
        var wait = new WebDriverWait(driver, Duration.ZERO.plusSeconds(wait_s));
        Function<WebDriver, Boolean> reserveButtonExistent = driver -> elementExists(elementID);
        wait.until(reserveButtonExistent);
    }

    public void goToFirstLocationDetails() {
        driver.findElement(By.id("sidebar_item_locations")).click();
        driver.findElement(By.xpath("((//table[1]/tbody/tr)[1])//td[2]//a")).click();
    }

    public void goToWorkstationsList() {
        goToFirstLocationDetails();
        driver.findElement(By.id("stations-tab")).click();
        waitForJSTabLoad();
    }

    public void clearAndEnterText(String textBoxID, String content) {
        var element = driver.findElement(By.id(textBoxID));
        element.clear();
        element.sendKeys(content);
    }

    public void goToTab(String tabDivID, String workstationName) {
        driver.findElement(By.id(tabDivID + "-tab")).click();
        waitForJSTabLoad();
        selectWorkstationTab(tabDivID, workstationName);
    }

    public boolean alertDangerErrorPresent() {
        return elementExists(By.xpath("//div[contains(@class, 'alert-danger')]"));
    }

    public void clickOnBreadcrumb() {
        driver.findElement(By.xpath("//li[contains(@class, 'breadcrumb-item')]/a")).click();
    }

    public boolean addMondayShiftToCurrentWorkstation(String from, String to) {
        driver.findElement(By.xpath(getSelectedChildPillXPath() + "//div[@class='addStationIcon'][1]/parent::div")).click();
        if (!from.isEmpty() && !to.isEmpty()) {
            clearAndEnterText("from", from);
            clearAndEnterText("to", to);
        }

        driver.findElement(By.id("addWorkingTimeSubmitButton")).click();
        boolean failed = alertDangerErrorPresent();
        if (failed) {
            clickOnBreadcrumb();
        }
        waitForJSTabLoad();
        return !failed;
    }

    public void addMondayShiftToCurrentWorkstation() {
        addMondayShiftToCurrentWorkstation("", "");
    }

    public void deleteFirstMondayShiftOfCurrentWorkstation() {
        driver.findElement(By.xpath(getSelectedChildPillXPath() + "//div[@class='workingHours'][1]")).click();
        driver.findElement(By.id("workingtime_delete_button")).click();
        waitForJSTabLoad();
    }

    public void deleteFirstWorkingExceptionOfCurrentWorkstation() {
        deleteViaThreeDots(getSelectedChildPillXPath() + "//tr[1]//td[last()]");
    }

    public boolean addWorkingExceptionToCurrentWorkstation(String date) {
        driver.findElement(By.xpath(getSelectedChildPillXPath() + "//a[contains(@class, 'btn')][1]")).click();
        driver.findElement(By.id("closed")).click(); //select closed
        clearAndEnterText("dateInput", date);
        driver.findElement(By.id("addButton")).click();
        boolean failed = alertDangerErrorPresent();
        if (failed) {
            clickOnBreadcrumb();
        }
        waitForJSTabLoad();
        return !failed;
    }

    void selectWorkstationTab(String tabDivID, String workstationName) {
        driver.findElement(By.xpath(String.format("//div[@id='%s']", tabDivID) + String.format("//a[contains(text(), '%s')]/parent::li", workstationName))).click();
        waitForJSTabLoad();
    }

    public void addWorkstation(String name) {
        goToWorkstationsList();
        driver.findElement(By.id("addWorkstationButton")).click();
        clearAndEnterText("name", name);
        driver.findElement(By.id("addWorkstationSubmitButton")).click();
        waitForJSTabLoad();
    }

    public void assignWorkstationToFirstService(String workstationName) {
        driver.findElement(By.id("services-tab")).click();
        waitForJSTabLoad();
        selectWorkstationTab("services", workstationName);
        driver.findElement(By.xpath(getSelectedChildPillXPath() + "//a[contains(@class, 'btn')]")).click();
        driver.findElement(By.xpath("//input[@class='form-check-input'][1]")).click();
        driver.findElement(By.id("saveButton")).click();
    }

    void deleteViaThreeDots(String lastTDXpath) {
        driver.findElement(By.xpath(lastTDXpath + "//a")).click();
        driver.findElement(By.xpath(lastTDXpath + "//div[@class='dropdown-menu show']//a[last()]")).click();
        waitForJSTabLoad();
    }

    void deleteViaThreeDotsByText(String text) {
        deleteViaThreeDots(String.format("//td[contains(text(), '%s')]/parent::tr//td[last()]", text));
    }

    void deleteWorkstation(String workstationName) {
        goToWorkstationsList();
        deleteViaThreeDotsByText(workstationName);
    }

    //Returns true if the appointment was successfully reserved, false otherwise
    boolean reserveAppointment(String workstationName, String time, String name, String surname) {
        driver.findElement(By.id("sidebar_item_selfBooking")).click();
        //pick first category
        driver.findElement(By.xpath("//div[contains(@class, 'clickable-card')][1]")).click();
        //pick first service
        driver.findElement(By.xpath("//div[contains(@class, 'clickable-card')][1]")).click();
        driver.findElement(By.id("i_understand_button")).click();
        //pick last workstation
        driver.findElement(By.xpath(String.format("//div[contains(text(), '%s')]/ancestor::div[contains(@class, 'clickable-card')]", workstationName))).click();

        waitForSpinnerToDisappear();
        try {
            driver.findElement(By.xpath("//div[contains(@class, 'clickable-card')][last()]")).click();
            waitForSpinnerToDisappear();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.out.println("The available times api fetch took longer than " + SPINNER_WAIT_TIME_S + " seconds (elements intractable)");
            throw e;
        }
        driver.findElement(By.xpath(String.format("//button[contains(text(), '%s')]", time))).click();
        waitUntilExistent("reserveButton", 20);
        driver.findElement(By.id("reserveButton")).click();
        driver.findElement(By.id("person_1_name")).sendKeys(name);
        driver.findElement(By.id("person_1_surname")).sendKeys(surname);
        driver.findElement(By.id("completeReservationButton")).click();
        try {
            waitUntilExistent("check_icon_div", 10);
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }

        return true;
    }

    public void deleteUser(String email) {
        deleteViaThreeDotsByText(email);
        waitForJSTabLoad();
        driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']")).click();
    }
}
