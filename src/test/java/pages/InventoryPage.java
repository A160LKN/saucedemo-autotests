package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {

    private final By title = By.className("title");
    private final By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить заголовок страницы товаров")
    public String getTitle() {
        return waitForVisible(title).getText();
    }

    @Step("Добавить рюкзак в корзину")
    public void addBackpackToCart() {
        click(addBackpackButton);
    }

    @Step("Удалить рюкзак из корзины")
    public void removeBackpackFromCart() {
        click(removeBackpackButton);
    }

    @Step("Получить количество товаров на значке корзины")
    public int getCartCount() {
        return Integer.parseInt(waitForVisible(cartBadge).getText());
    }

    @Step("Проверить, что значок корзины скрыт")
    public boolean isCartBadgeHidden() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBadge));
    }

    @Step("Открыть корзину")
    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }
}