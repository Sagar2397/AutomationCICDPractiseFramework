package testingacademy.StepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testingacademy.TestComponents.BaseTest;
import testingacademy.pageobjects.CartPage;
import testingacademy.pageobjects.CheckoutPage;
import testingacademy.pageobjects.ConfirmationPage;
import testingacademy.pageobjects.LandingPage;
import testingacademy.pageobjects.ProductCatalog;

public class StepDefinationImple extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog productcatalog;
	public ConfirmationPage confirmorder;
	
	//Given : I landed on Ecommerce Page
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		
		landingPage = launchApplication();
		
	}
	
	//Given  Logged in with username <name> and password <pasword>
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username , String password) {
		
		 productcatalog= landingpage.loginApplication(username,password);
		
	}
	
	//When  I add product <productName> to cart 
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) {
		
		List<WebElement> products = productcatalog.getProductList();
        productcatalog.addProductToCart(productName);
        
	}
	
	//And Checkout <productName> and submit the order
	@When ("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		
		 	CartPage cartpage = productcatalog.goToCart();
	        Boolean match = cartpage.verifyProductDisplay(productName);
	      
	        Assert.assertTrue(match);
	        CheckoutPage checkoutpage = cartpage.goToCheckout();
	        confirmorder= checkoutpage.checkouPageInfo();
	        confirmorder.confirmOrder();
	}
	
	//@Then ("Thank you for your order!" message is displayed on ConfirmationPage)
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationpage(String string) {
		String ConfirmMessage = confirmorder.VerifyConfirmationMessage();
        Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(string));
        driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_displayed(String strAgr1) {
		 Assert.assertEquals(strAgr1 , landingpage.getErrorMessage());
	}
	
}
