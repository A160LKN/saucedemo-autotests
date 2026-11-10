package utils;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import tests.BaseTest;

import java.io.ByteArrayInputStream;

public class ScreenshotOnFailureExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof BaseTest baseTest && baseTest.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) baseTest.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Скриншот при падении", "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        }

        throw throwable;
    }
}