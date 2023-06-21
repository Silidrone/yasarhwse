import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AutomatedTest {
    protected static WebDriver driver;
    protected static final String username = "seleniumtestuser@mailinator.com";
    protected static final String password = "test98125!";

    protected static final String baseURL = "zamaninda.com";

    protected static SeleniumApsUtil seleniumApsUtil;

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        seleniumApsUtil = new SeleniumApsUtil(driver);
        driver.get(String.format("https://%s/login", baseURL));
        //important, otherwise elements might not be in the viewport, and you might get "element click intercepted" errors
        driver.manage().window().maximize();
        seleniumApsUtil.clearAndEnterText("email", username);
        seleniumApsUtil.clearAndEnterText("password", password);
        driver.findElement(By.id("loginButton")).click();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}