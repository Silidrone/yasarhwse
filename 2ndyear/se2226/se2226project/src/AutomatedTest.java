import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AutomatedTest {
    protected static WebDriver driver;
    protected static final String username = "melisozveri@gmail.com";
    protected static final String password = "melisozveri123";

    protected static SeleniumApsUtil seleniumApsUtil;

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        seleniumApsUtil = new SeleniumApsUtil(driver);
        driver.get("http://76.72.163.151:8060/login");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        seleniumApsUtil.clearAndEnterText("email", username);
        seleniumApsUtil.clearAndEnterText("password", password);
        driver.findElement(By.id("loginButton")).click();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}