package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.TestData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("SauceDemo")
@Feature("Корзина")
public class CartTest extends BaseTest {

    private InventoryPage inventory;

    @BeforeEach
    void loginAsStandardUser() {
        new LoginPage(driver).open().login(TestData.STANDARD_USER, TestData.PASSWORD);
        inventory = new InventoryPage(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Товар добавляется в корзину")
    void addProductToCart() {
        inventory.addBackpackToCart();

        assertEquals(1, inventory.getCartCount());

        CartPage cart = inventory.openCart();
        assertTrue(cart.getItemNames().contains(TestData.BACKPACK));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Товар удаляется из корзины")
    void removeProductFromCart() {
        inventory.addBackpackToCart();
        inventory.removeBackpackFromCart();

        assertTrue(inventory.isCartBadgeHidden());
    }
}