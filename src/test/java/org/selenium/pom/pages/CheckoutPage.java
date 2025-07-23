package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;


public class CheckoutPage extends BasePage {

    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By addressField = By.id("billing_address_1");
    private final By cityField = By.id("billing_city");
    private final By postalCodeField = By.id("billing_postcode");
    private final By emailField = By.id("billing_email");
    private final By placeOrderButton = By.cssSelector("div[class=woocommerce-terms-and-conditions-wrapper] + button");
    private final By successNotice = By.cssSelector(".woocommerce-order > .woocommerce-notice--success");

    private final By clickHereToLoginLink = By.cssSelector(".woocommerce-info a");
    private final By userNameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector(".woocommerce-button");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    private final By countryDropdown = By.id("billing_country");
    private final By stateDropdown = By.id("billing_state");

    private final By directBankTransferButton = By.id("payment_method_bacs");

    private final By productName = By.cssSelector("td[class='product-name']");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public CheckoutPage load(){
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        WebElement element = waitForElementToBeVisible(firstNameField);
        element.clear();
        element.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        WebElement element = waitForElementToBeVisible(lastNameField);
        element.clear();
        element.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(countryDropdown)));
        select.selectByVisibleText(countryName);

        return this;
    }

    public CheckoutPage selectState(String stateName) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(stateDropdown)));
        select.selectByVisibleText(stateName);

        return this;
    }

    public CheckoutPage enterAddress(String addressName) {
        WebElement element = waitForElementToBeVisible(addressField);
        element.clear();
        element.sendKeys(addressName);
        return this;
    }

    public CheckoutPage enterCity(String cityName) {
        WebElement element = waitForElementToBeVisible(cityField);
        element.clear();
        element.sendKeys(cityName);
        return this;
    }

    public CheckoutPage enterPostalCode(String postalCode) {
        WebElement element = waitForElementToBeVisible(postalCodeField);
        element.clear();
        element.sendKeys(postalCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        WebElement element = waitForElementToBeVisible(emailField);
        element.clear();
        element.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterAddress(billingAddress.getAddress()).
                enterCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterPostalCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());

    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        wait.until(ExpectedConditions.elementToBeClickable((placeOrderButton))).click();
        return this;
    }

    public String getNotice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        waitForElementToBeClickable(clickHereToLoginLink).click();
        return this;
    }

    public CheckoutPage enterUserName(User user) {
        WebElement element = waitForElementToBeLocated(userNameField);
        element.clear();
        element.sendKeys(user.getUserName());
        return this;
    }

    public CheckoutPage enterPassword(User user) {
        WebElement element = waitForElementToBeLocated(passwordField);
        element.clear();
        element.sendKeys(user.getPassword());
        return this;
    }

    public CheckoutPage clickLoginButton() {
        waitForElementToBeClickable(loginButton).click();
        return this;
    }

    public CheckoutPage login(User user) {
        return enterUserName(user).
                enterPassword(user).
                clickLoginButton();
    }

    public CheckoutPage selectBankTransfer() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferButton));
        if (!element.isSelected()) {
            element.click();
        }
        return this;
    }
    public String getProductName(){
       return waitForElementToBeVisible(productName).getText();
    }
}
