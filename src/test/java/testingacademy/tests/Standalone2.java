package testingacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import testingacademy.pageobjects.LandingPage;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalone2 {

    public static void main(String[] args) throws InterruptedException {
        
        String productName = "Sauce Labs Backpack";
        
        // Setup Chrome with disabled password manager
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
        options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
            put("credentials_enable_service", false);
            put("profile.password_manager_enabled", false);
        }});
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        driver.get("https://www.saucedemo.com/");
        LandingPage landingpage = new LandingPage(driver);
        
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        // Find all products
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item")));
        List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item"));
        
        // Find target product
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.cssSelector(".inventory_item_name"))
                .getText().equals(productName))
                .findFirst()
                .orElse(null);
        
        if (prod != null) {
            // Click add-to-cart inside the product element
            prod.findElement(By.cssSelector("button.btn_inventory")).click();
            System.out.println(productName + " added to cart!");
        } else {
            System.out.println(productName + " not found!");
        }
        
       
        
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        
        List<WebElement> cartitems = driver.findElements(By.cssSelector(".inventory_item_name"));
        
        Thread.sleep(2000);
        
        Boolean match = cartitems.stream().anyMatch(cartitem -> cartitem.getText().equalsIgnoreCase(productName)) ;
        Assert.assertTrue(match);
        
       
        String cartProductName = driver.findElement(By.cssSelector(".inventory_item_name")).getText();   // To get the text of cart product
        
        

        // Validation
        if (cartProductName.equals(productName)) {
            System.out.println("✅ Product successfully added to cart: " + cartProductName);
        } else {
            System.out.println("❌ Validation failed! Expected: " + productName + " but found: " + cartProductName);
        }
       
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Sagar");
        driver.findElement(By.id("last-name")).sendKeys("Johny");
        driver.findElement(By.id("postal-code")).sendKeys("411052");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        
        String Text = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
        System.out.println(Text);
    }
}
