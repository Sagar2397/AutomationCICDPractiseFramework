package testingacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingacademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item"));
	
	By productsBy =By.cssSelector(".inventory_item");
	By addToCart = By.cssSelector("button.btn_inventory");
	
	//PageFactory
	
	@FindBy (css=".inventory_item")
	List<WebElement> products;
	
	@FindBy(css=".shopping_cart_link")
	WebElement Carticon;
	
	//below method will simply get the product list 
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName( String productName) {
		WebElement prod = getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector(".inventory_item_name"))
                .getText().equals(productName))
                .findFirst()
                .orElse(null);
		return prod;
		
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		
        
	}
	 
	public CartPage goToCart() {
		
		Carticon.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
}

