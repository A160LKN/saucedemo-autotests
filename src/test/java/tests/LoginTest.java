package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.InventoryPage;
import pages.LoginPage;
import utils.TestData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("SauceDemo")
@Feature("Авторизация")
public class LoginTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешный вход с верными данными")
    void successfulLogin() {
        new LoginPage(driver).open().login(TestData.STANDARD_USER, TestData.PASSWORD);

        InventoryPage inventory = new InventoryPage(driver);

        assertEquals("Products", inventory.getTitle());
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @ParameterizedTest(name = "{0}")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Ошибки при входе")
    @CsvSource(delimiter = '|', textBlock = """
            Неверный пароль              | standard_user   | wrong_password | Username and password do not match
            Заблокированный пользователь | locked_out_user | secret_sauce   | Sorry, this user has been locked out
            Пустой логин                 | ''              | secret_sauce   | Username is required
            Пустой пароль                | standard_user   | ''             | Password is required
            """)
    void negativeLogin(String caseName, String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.login(username, password);

        assertTrue(loginPage.getErrorText().contains(expectedError),
                "Ожидалась ошибка: " + expectedError);
    }
}