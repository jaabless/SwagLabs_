package com.swaglabs.tests;


import com.swaglabs.pages.CheckoutPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.InventoryPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.base.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutTests extends BaseTest {

    private void loginAndAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.enterCredentials("standard_user", "secret_sauce");
        loginPage.clickLogin();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
    }

    @ParameterizedTest
    @MethodSource("com.swaglabs.data.CheckoutTestData#checkoutTestData")
    @DisplayName("Test checkout functionality with various form inputs")
    @Story("Checkout")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutForm(String firstName, String lastName, String zipCode, boolean shouldSucceed, String expectedResult) {
        loginAndAddToCart();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        assertTrue(checkoutPage.isCheckoutInfoPageDisplayed(), "Checkout info page not displayed");

        checkoutPage.enterCheckoutInfo(firstName, lastName, zipCode);
        checkoutPage.clickContinue();

        if (shouldSucceed) {
            checkoutPage.clickFinish();
            assertTrue(checkoutPage.isConfirmationPageDisplayed(), "Confirmation page not displayed");
            assertTrue(checkoutPage.getConfirmationMessage().contains("Thank you for your order"), "Unexpected confirmation message");
        } else {
            assertEquals(expectedResult, checkoutPage.getErrorMessage(), "Unexpected error message");
        }
    }

    @Test
    @DisplayName("Verify checkout without login redirects to login page")
    @Story("Checkout")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckoutWithoutLogin() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginPageDisplayed(), "Expected to be redirected to login page");
    }
}
