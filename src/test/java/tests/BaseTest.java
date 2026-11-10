package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ScreenshotOnFailureExtension;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseTest {

    // показываем только серьёзные ошибки Selenium, предупреждения скрываем
    private static final Logger SELENIUM_LOGGER = Logger.getLogger("org.openqa.selenium");

    static {
        SELENIUM_LOGGER.setLevel(Level.SEVERE);
    }

    protected WebDriver driver;

    @BeforeEach
    void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        // отключаем всплывающее окно Chrome «смените пароль» после входа
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false
        ));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}