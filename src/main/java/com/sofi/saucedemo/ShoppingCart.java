package com.sofi.saucedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCart extends BasePage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class.getName());

    @FindBy(css = "div[class='subheader_label']")
    public WebElement yourCartHeader;

    @FindBy(xpath = "//div[contains(text(),'Sauce Labs Onesie')]")
    public WebElement product1;

    @FindBy(xpath = "//div[contains(text(),'Sauce Labs Bike Light')]")
    public WebElement product2;

    @FindBy(css = "a[class='cart_checkout_link']")
    public WebElement checkoutButton;

    public ShoppingCart(WebDriver driver){
        super(driver);
    }

    public boolean yourCartHeaderMatches(String header){
        boolean result = false;
        if(header.contains(yourCartHeader.getText())) {
            logger.debug("Verify Shopping cart page header : " + yourCartHeader.getText() + " with " +header);
            result = true;
        }
        return result;
    }

    public boolean cartProductsMatches(String item1 , String item2){
        boolean matches = false;
        if(item1.contains(product1.getText()) && item2.contains(product2.getText()) ) {
            logger.debug("Verify shopping cart items are correct" + product1.getText() + " with " +item1);
            logger.debug("Verify shopping cart items are correct" + product2.getText() + " with " +item2);
            matches = true;
        }
        return matches;
    }

    public boolean checkoutButtonPresent(String button){
        boolean found = false;
        if(button.contains(checkoutButton.getText())) {
            logger.debug("CHECKOUT button clicked");
            found = true;
        }
        return found;
    }
}
