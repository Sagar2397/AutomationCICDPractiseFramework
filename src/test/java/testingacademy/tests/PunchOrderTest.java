package testingacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import testingacademy.TestComponents.BaseTest;
import testingacademy.pageobjects.CartPage;
import testingacademy.pageobjects.CheckoutPage;
import testingacademy.pageobjects.ConfirmationPage;
import testingacademy.pageobjects.LandingPage;
import testingacademy.pageobjects.ProductCatalog;

import java.time.Duration;
import java.util.List;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PunchOrderTest extends BaseTest {
	 String productName = "Sauce Labs Backpack";
		
	@Test(dataProvider="getData", groups= {"Purchase"})
	//public void punchOrder(String email , String password , String productName) throws IOException 
	public void punchOrder(HashMap<String,String> input) throws IOException{
        
       // LandingPage landingpage = launchApplication(); this line is removed because we have created a landing page object as public in base test
        
        ProductCatalog productcatalog= landingpage.loginApplication(input.get("username"),input.get("password"));
        
        List<WebElement> products = productcatalog.getProductList();
        productcatalog.addProductToCart(input.get("product"));
        CartPage cartpage = productcatalog.goToCart();
       
        
        Boolean match = cartpage.verifyProductDisplay(input.get("product"));
      
        Assert.assertTrue(match);
        CheckoutPage checkoutpage = cartpage.goToCheckout();
                
        ConfirmationPage confirmorder= checkoutpage.checkouPageInfo();

        confirmorder.confirmOrder();
        String ConfirmMessage = confirmorder.VerifyConfirmationMessage();
        Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thank you for your order!")); 
    }
		
		
	
	
		
		@DataProvider
 		public Object[][] getData() throws IOException {
			
			List<HashMap <String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\testingacademy\\data\\PurchaseOrder.json");
			return new Object[] [] {{data.get(0)} ,{data.get(1) }};
			
 		}
		
}			
		/*@DataProvider
 		public Object[][] getData() {
 		
 			//return new Object[] [] {{"standard_user" ,"secret_sauce" , "Sauce Labs Backpack"} ,{"standard_user" ,"secret_sauce" , "Sauce Labs Bike Light"}}; //with the help of 2D Array
 			  }
 			 */
		/*
		 * @DataProvider
 		public Object[][] getData() {
 			
		//	HashMap<String,String> map =new HashMap<String,String>();
		//	map.put("username","standard_user");
		//	map.put("password", "secret_sauce");
		//	map.put("product", "Sauce Labs Backpack");
			
		//	HashMap<String,String> map1 =new HashMap<String,String>();
		//	map1.put("username","standard_user");
		//	map1.put("password", "secret_sauce");
		//	map1.put("product", "Sauce Labs Bike Light");
		 */
//}
		
		
	
 		