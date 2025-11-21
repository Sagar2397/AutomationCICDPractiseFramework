package testingacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import testingacademy.TestComponents.BaseTest;
import testingacademy.pageobjects.CartPage;
import testingacademy.pageobjects.ProductCatalog;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
		 public void LoginErrorValidation() {
		
		landingpage.loginApplication("standard_usr", "secret_sauce");
		
        landingpage.getErrorMessage();
        
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service" , landingpage.getErrorMessage());
        

	}

	
	@Test
	
		public void ProductErrorValidation() throws IOException {
    
        
        String productName = "Sauce Labs Backpack";
        
       // LandingPage landingpage = launchApplication(); this line is removed because we have created a landing page object as public in b
        
        ProductCatalog productcatalog= landingpage.loginApplication("problem_user", "secret_sauce");
        
        List<WebElement> products = productcatalog.getProductList();
        productcatalog.addProductToCart(productName);
        CartPage cartpage = productcatalog.goToCart();
       
        
        Boolean match = cartpage.verifyProductDisplay("Sauce Labs Backpack");
      
        Assert.assertTrue(match);

	}
}
