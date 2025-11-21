package testingacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	 @FindBy (id="finish")
	 WebElement confirm;
	 
	 @FindBy (xpath="//h2[@class='complete-header']")
	 WebElement ConfirmationMessage;
	
	 public void confirmOrder() {
		 
		 confirm.click();
		
	 }
	 	
	 public String VerifyConfirmationMessage() {
		 return ConfirmationMessage.getText();
	 }

}
