package testingacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	//String productName = "Sauce Labs Backpack";
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id="checkout")
	 WebElement checkout;
	
	@FindBy (css=".inventory_item_name")
	List<WebElement> cartitems;
	
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match = cartitems.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
		
		
	}
	
	public CheckoutPage goToCheckout() {
		
		 checkout.click();
		 CheckoutPage checkoutpage = new CheckoutPage(driver);
		 return checkoutpage;
	}

}
