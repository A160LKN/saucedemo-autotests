package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    private final By itemNames = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить названия товаров в корзине")
    public List<String> getItemNames() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemNames))
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}