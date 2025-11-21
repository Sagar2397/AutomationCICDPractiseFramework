package testingacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement username = driver.findElement(By.id("user-name"));
	//(By.id("password")).sendKeys("secret_sauce");
	
	//PageFactory
	@FindBy (id="user-name")
	WebElement username;
	
	@FindBy (id="password")
	WebElement passwordEle;
	
	@FindBy (id="login-button")
	WebElement login;
	
	@FindBy (css="div.error-message-container.error")
	WebElement errorMessage;
	
	
	public ProductCatalog loginApplication(String email , String password) {
		
		username.sendKeys(email);
		passwordEle.sendKeys(password);
		login.click();
		ProductCatalog productcatalog = new ProductCatalog(driver);
		return productcatalog;
		
	}
	
	public void goTo() {
		 driver.get("https://www.saucedemo.com/");
	}
	
	public String getErrorMessage() {
		
		return errorMessage.getText();
		
	}
	
}

