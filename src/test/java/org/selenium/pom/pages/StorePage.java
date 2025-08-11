package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.components.ProductThumbnail;

import java.io.IOException;


public class StorePage extends BasePage {
    private final By searchField = By.cssSelector("form[role='search'] input[type='search']");
    private final By searchButton = By.cssSelector("button[value = 'Search']");
    private final By title = By.cssSelector(".woocommerce-products-header h1");
    private final By infoTxt = By.cssSelector(".woocommerce-info");
    private ProductThumbnail productThumbnail;

    public ProductThumbnail getProductThumbnail() {

        return productThumbnail;
    }

    public ProductPage searchExactMatch(String txt) {
        enterTxtInSearchField(txt).clickSearchButton();
        return new ProductPage(driver);
    }


    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public Boolean isLoaded() {
        return wait.until(ExpectedConditions.urlContains("/store"));
    }

    public StorePage load() {
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

    public StorePage search(String txt) {
        enterTxtInSearchField(txt).
                clickSearchButton();
        return this;
    }

    public ProductPage navigateToTheProduct(Integer id) throws IOException {
        waitForElementToBeClickable(By.xpath("//h2[normalize-space()='" + new Product(id).getName() + "']")).click();
        return new ProductPage(driver);
    }

    public String getInfo() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(infoTxt)).getText();
    }

}
