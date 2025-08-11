package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;

public class ProductThumbnail extends BasePage {
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCartButtonElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public ProductThumbnail clickAddToCartButton(String productName){
        By addToCartBtn = getAddToCartButtonElement(productName);
        waitForElementToBeClickable(addToCartBtn).click();
        return this;
    }

    public CartPage clickViewCart(){
       waitForElementToBeClickable(viewCartLink).click();
        return new CartPage(driver);
    }
}
