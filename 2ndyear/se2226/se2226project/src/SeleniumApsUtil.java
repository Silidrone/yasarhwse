import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.fail;

public class SeleniumApsUtil {
    final int SPINNER_WAIT_TIME_S = 10;
    protected WebDriver driver;

    SeleniumApsUtil(WebDriver driver) {
        this.driver = driver;
    }

    String getSelectedPillXPath(String parentID) {
        return "//div[@id='" + parentID + "']//div[@id='pills-tabContent']//div[@class='tab-pane fade active show']";
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
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement clearAndEnterText(String textBoxID, String content) {
        var element = driver.findElement(By.id(textBoxID));
        element.clear();
        element.sendKeys(content);
        return element;
    }

    public boolean addMondayShift(String workstationName, String from, String to) {
        driver.findElement(By.id("workingHours-tab")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        selectWorkstationTab("workingHours", workstationName);
        driver.findElement(By.xpath(getSelectedPillXPath("workingHours") + "//div[@class='workingHours'][1]")).click();
        if(!from.isEmpty() && !to.isEmpty()) {
            clearAndEnterText("from", from);
            clearAndEnterText("to", to);
        }

        driver.findElement(By.id("addWorkingTimeSubmitButton")).click();
        if(elementExists(By.xpath("//div[contains(@class, 'alert-danger')]"))) return false;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public void addMondayShift(String workstationName) {
        addMondayShift(workstationName, "", "");
    }

    void selectWorkstationTab(String tabDivID, String workstationName) {
        driver.findElement(By.xpath(String.format("//div[@id='%s']", tabDivID) + String.format("//a[contains(text(), '%s')]/parent::li", workstationName))).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWorkstation(String name) {
        goToWorkstationsList();
        driver.findElement(By.id("addWorkstationButton")).click();
        var nameTextBoxElement = driver.findElement(By.id("name"));
        nameTextBoxElement.click();
        nameTextBoxElement.sendKeys(name);
        driver.findElement(By.id("addWorkstationSubmitButton")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void assignWorkstationToFirstService(String workstationName) {
        driver.findElement(By.id("services-tab")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        selectWorkstationTab("services", workstationName);
        driver.findElement(By.xpath(getSelectedPillXPath("services") + "//a[contains(@class, 'btn')]")).click();
        driver.findElement(By.xpath("//input[@class='form-check-input'][1]")).click();
        driver.findElement(By.id("saveButton")).click();
    }

    void deleteWorkstation(String workstationName) {
        goToWorkstationsList();
        String lastTDXpath = String.format("//td[contains(text(), '%s')]/parent::tr//td[last()]", workstationName);
        driver.findElement(By.xpath(lastTDXpath + "//a")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath(lastTDXpath + "//div[@class='dropdown-menu show']//a[last()]")).click();
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
        var nameTextBox = driver.findElement(By.id("person_1_name"));
        nameTextBox.click();
        nameTextBox.sendKeys(name);
        var surnameTextBox = driver.findElement(By.id("person_1_surname"));
        surnameTextBox.click();
        surnameTextBox.sendKeys(surname);
        driver.findElement(By.id("completeReservationButton")).click();
        try {
            waitUntilExistent("check_icon_div", 10);
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }

        return true;
    }
}
