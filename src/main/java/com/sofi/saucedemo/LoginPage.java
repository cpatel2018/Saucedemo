package com.sofi.saucedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Properties;


public class LoginPage extends BasePage {

    ProductsPage productsPage;
    private static final Logger logger = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage(WebDriver driver) {
        super(driver);
        productsPage = new ProductsPage(driver);
    }

    @FindBy(css = "input[type='text']")
    public WebElement userNameText;

    @FindBy(css = "input[type='password']")
    public WebElement passwordText;

    @FindBy(css = "input[type='submit']")
    public WebElement loginButton;


    public ProductsPage login(String username, String password){
        userNameText.sendKeys(username);
        logger.info("Username text box found");

        passwordText.sendKeys(password);
        logger.info("Password text box found");

        loginButton.submit();
        logger.info("Login button found");
        return productsPage;
    }

}
