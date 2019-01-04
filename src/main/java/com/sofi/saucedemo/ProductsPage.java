package com.sofi.saucedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

    ShoppingCart shoppingCart;
    private static final Logger logger = LogManager.getLogger(LoginPage.class.getName());

    public ProductsPage(WebDriver driver){
        super(driver);
        shoppingCart = new ShoppingCart(driver);
    }

    @FindBy(xpath = "//div//div[@class='inventory_list']//div[5]//div[3]//button[1]")
    public WebElement sauceLabsOnesie;

    @FindBy(xpath = "//div//div[@class='inventory_list']//div[2]//div[3]//button[1]")
    public WebElement sauceLabBikeLight;

    @FindBy(xpath = "//*[contains(@class,'svg-inline--fa fa-shopping-cart fa-w-18 fa-3x')]")
    public WebElement cart;

    @FindBy(css = "div[class='product_label']")
    public WebElement productsHeader;


    public void addToCart(String item1 , String item2){
        sauceLabsOnesie.click();
        logger.debug("sauceLabs Onesie is selected");
        sauceLabBikeLight.click();
        logger.debug("sauceLab BikeLight is selected");
        cart.click();
        logger.debug("Cart Icon is clicked");
    }

    public boolean productHeaderMatches(String title){
        boolean result = false;
        if(title.contains(productsHeader.getText())) {
            //System.out.println("UI :"+productsHeader.getText() +" matches " + title);
            logger.debug("Product Page title matches");
            result = true;
        }else {
            logger.error("Product Page title  did not matches");
        }
        return result;
    }

}
