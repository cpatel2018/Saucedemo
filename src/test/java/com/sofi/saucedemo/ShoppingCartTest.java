package com.sofi.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.util.Properties;

/*
Test case:
        1. Use the website https://www.saucedemo.com/ as the test site
        2. Create a test that logs in as the user: standard_user password: secret_sauce
        3. Add the “Sauce Labs Onesie” to the cart
        4. Add the “Sauce Labs Bike Light” to the cart
        5. Verify both items are in the cart

The requirements for the assessment are as follows:
1. Use Java (preferred) or Python and pick whatever test framework you’re most familiar
with
2. Check your code into a free account on GitHub or Bitbucket and send us the link to the
repo. You can include the libraries/framework/tests for this test in the same repo as the
API tests or a different one.
3. Use WebDriver
4. Tests should be runnable right out of the box. (i.e. I should be able clone your repo and
run the tests)
5. Don’t forget to include instructions on how to execute the tests!
6. Tests should use good OOP principles and have some abstraction in the testing layout so
that they could be a base/template for implementing many automated UI tests against
the same website.
7. Clear, concise code is important.
8. ** Design and layout counts:
layout the test code so that the tests are abstracted (for maintainability and readability).
Pay attention to what libraries/helper classes and base classes would he abstracted to achieve this goal.
*/

// SCREENCAST of Successful run : https://screencast.com/t/XptOMHcXKPW

public class ShoppingCartTest {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(ShoppingCartTest.class.getName());
    Properties prop = new Properties();


    @BeforeMethod
        public void setup() {
        try {
            loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("Setting chrome driver property");
            System.setProperty("webdriver.chrome.driver",  prop.getProperty("driver.path"));
            driver = new ChromeDriver();
            driver.get(prop.getProperty("URL"));
            logger.debug("Go to "+prop.getProperty("URL"));

    }

        @Test
        public void testShoppingCartSuccess() {
            LoginPage login = new LoginPage(driver);
            login.login(prop.getProperty("username"), prop.getProperty("password"));
            logger.info("Login button Pressed");

            ProductsPage products = new ProductsPage(driver);
            Assert.assertTrue(products.productHeaderMatches(prop.getProperty("product.page.header")));
            logger.info("On Products Page");
            products.addToCart(prop.getProperty("product1.name"),prop.getProperty("product2.name"));
            logger.info("Added to products to cart");

            ShoppingCart cart = new ShoppingCart(driver);
            Assert.assertTrue(cart.yourCartHeaderMatches(prop.getProperty("cart.page.header")));
            logger.info("On Shopping cart page");
            Assert.assertTrue(cart.cartProductsMatches(prop.getProperty("product1.name"),prop.getProperty("product2.name")));
            Assert.assertTrue(cart.checkoutButtonPresent(prop.getProperty("checkout.button.label")));

        }

        @AfterMethod
        private void teardown() {
            if (driver != null) {
               driver.quit();
            }
        }

    private void loadProperties() throws IOException {
        prop = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream("dataFile.properties");
        prop.load(in);
        in.close();

    }
}

