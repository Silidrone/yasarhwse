import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RegisteringUserTest extends AutomatedTest {
    private static Stream<Arguments> userInfoStream() {
        return Stream.of(
                Arguments.of("Muhamed Cicak", "silidrone@gmail.com", "E*c72TjAh", "E*c72TjAh", true),  // T, T, T, YES
                Arguments.of("Muhamed Cicak", "silidrone@gmail.com", "E*c72TjAh", "E*c72TgAh", false), // T, T, F, NO
                Arguments.of("Muhamed Cicak", "silidrone@gmail.com", "E*c72", "E*c72", false), // T, F, T, NO
                Arguments.of("Muhamed Cicak", "silidrone@gmail.com", "E*c72T", "E*c7", false), // T, F, F, NO
                Arguments.of("Melis Ozveri", "melisozverigmail", "E*c72TjAh", "E*c72TjAh", false), // F, T, T, NO
                Arguments.of("Melis Ozveri", "melisozveri", "E*c72TjAh", "E*c12TjAh", false), // F, T, F, NO
                Arguments.of("Melis Ozveri", "melisozverihotmail", "E*", "E*", false), // F, F, T, NO
                Arguments.of("Melis Ozveri", "@melisozveriyahoo@", "E", "A", false)  // F, F, F, NO
        );
    }

    @BeforeAll
    static void goToUsersPage() {
        driver.get("http://76.72.163.151:8060/users/show");
    }

    @ParameterizedTest
    @MethodSource("userInfoStream")
    void testRegisteringUser(String name, String email, String password, String passwordConfirmation, boolean expected) {
        driver.findElement(By.id("addButton")).click();
        seleniumApsUtil.clearAndEnterText("name", name);
        seleniumApsUtil.clearAndEnterText("email", email);
        seleniumApsUtil.clearAndEnterText("password", password);
        seleniumApsUtil.clearAndEnterText("password_confirmation", passwordConfirmation);
        Select roleSelect = new Select(driver.findElement(By.id("role_id")));
        roleSelect.selectByIndex(roleSelect.getOptions().size() - 1);
        driver.findElement(By.id("addButton")).click();
        boolean noError = !seleniumApsUtil.alertDangerErrorPresent();
        if (noError) {
            seleniumApsUtil.waitForJSTabLoad();
            seleniumApsUtil.deleteUser(email);
        } else {
            seleniumApsUtil.clickOnBreadcrumb();
            seleniumApsUtil.waitForJSTabLoad();
        }
        assertEquals(noError, expected);
    }
}
