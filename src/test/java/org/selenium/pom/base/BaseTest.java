package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.utils.CookieUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;

public class BaseTest {
    private ThreadLocal <WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }
    protected WebDriver getDriver(){
       return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser) {
        //using only for MVN cmd
        browser = System.getProperty("browser", browser);
        if(browser == null) browser = "CHROME";
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("CURRENT THREAD " + Thread.currentThread().getId() +
                "DRIVER = " + getDriver());

    }

    @AfterMethod
    public void quitDriver() {
        System.out.println("CURRENT THREAD " + Thread.currentThread().getId() +
                "DRIVER = " + getDriver());
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie : seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }

    }
}
