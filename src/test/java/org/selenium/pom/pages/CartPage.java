package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
    private final By productName = By.cssSelector("td[class = 'product-name'] a");
    private final By checkoutBtn = By.cssSelector(".wc-proceed-to-checkout a");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return waitForElementToBeLocated(productName).getText();
    }

    public CheckoutPage checkout() {
        waitForElementToBeClickable(checkoutBtn).click();
        return new CheckoutPage(driver);
    }
}
