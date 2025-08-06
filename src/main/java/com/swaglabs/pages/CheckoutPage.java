package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement zipCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterCheckoutInfo(String firstName, String lastName, String zipCode) {
        enterText(firstNameField, firstName);
        enterText(lastNameField, lastName);
        enterText(zipCodeField, zipCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void clickFinish() {
        click(finishButton);
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

    public String getConfirmationMessage() {
        return getElementText(confirmationMessage);
    }

    public boolean isCheckoutInfoPageDisplayed() {
        return firstNameField.isDisplayed() && lastNameField.isDisplayed() && zipCodeField.isDisplayed();
    }

    public boolean isConfirmationPageDisplayed() {
        return confirmationMessage.isDisplayed();
    }
}