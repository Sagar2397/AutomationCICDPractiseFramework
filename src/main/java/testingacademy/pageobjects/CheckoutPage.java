package testingacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id="first-name")
	WebElement firstname;
	
	@FindBy (id="last-name")
	WebElement lastname;
	
	@FindBy (id="postal-code")
	WebElement pincode;
	
	@FindBy (id="continue")
	WebElement continuee;
	
	
	public ConfirmationPage checkouPageInfo() {
		
       firstname.sendKeys("Sagar");
       lastname.sendKeys("Johny");
       pincode.sendKeys("411052");
       continuee.click();
       ConfirmationPage confirmorder = new ConfirmationPage(driver);
       return confirmorder;
       
	}
}
