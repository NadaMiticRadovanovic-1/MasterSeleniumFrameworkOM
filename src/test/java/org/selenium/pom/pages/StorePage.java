package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;


public class StorePage extends BasePage {
    private final By searchField = By.cssSelector("form[role='search'] input[type='search']");
    private final By searchButton = By.cssSelector("button[value = 'Search']");
    private final By title = By.cssSelector(".woocommerce-products-header h1");
    private final By viewCartLink = By.cssSelector("a[title = 'View cart']");


    public StorePage(WebDriver driver) {
        super(driver);
    }
    public Boolean isLoaded(){
       return wait.until(ExpectedConditions.urlContains("/store"));
    }
    public StorePage load(){
        load("/store");
        return this;
    }

    private StorePage enterTxtInSearchField(String txt) {
       waitForElementToBeVisible(searchField).sendKeys(txt);
        return this;
    }

    private StorePage clickSearchButton() {
     waitForElementToBeClickable(searchButton).click();
        return this;
    }

    public String getTitle() {
        return waitForElementToBeVisible(title).getText();
    }

    public By getAddToCartButtonElement(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCartButton(String productName) {
        By addToCartButton = getAddToCartButtonElement(productName);
        waitForElementToBeClickable(addToCartButton).click();
        return this;
    }

    public StorePage search(String txt) {
        enterTxtInSearchField(txt).
                clickSearchButton();
        return this;
    }

    public CartPage clickViewCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated((viewCartLink))).click();
        return new CartPage(driver);
    }

}
