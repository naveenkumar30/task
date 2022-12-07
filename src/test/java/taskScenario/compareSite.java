package taskScenario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class compareSite {
	
	
	public static WebDriver driver;
	public static Properties config;
	
	@BeforeTest
	public void launchBrowser() throws IOException
	{
		 WebDriverManager.chromedriver().setup();
	      ChromeOptions options = new ChromeOptions();
	      driver=new ChromeDriver(options);
		 
	      config=new Properties();
			FileInputStream input = new  FileInputStream("config.properties");
			config.load(input);
	}

  @Test
  public void amazon() throws InterruptedException 
  {
	
	   
	  //Flipkart
	  driver.navigate().to("https://www.flipkart.com/");
		 
	  //Maximize the Chrome Window
	  driver.manage().window().maximize();
	 
	  //login_cancel button
	  driver.findElement(By.xpath(config.getProperty("login_cancel_button"))).click();
	 
	  //Search Item
	 driver.findElement(By.name(config.getProperty("search_field"))).sendKeys("mi tv");
	 //Search icon
	driver.findElement(By.xpath(config.getProperty("flipkart_search_icon"))).click();
	
	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	
	 //Select Item
	 driver.findElement(By.xpath(config.getProperty("select_item"))).click();
	
	 TimeUnit.SECONDS.sleep(10);
	
	
	 //Switch Over Tabs
	 ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
     driver.switchTo().window(newTb.get(1));
     
	 System.out.println("*******FLIPKART***********");

	
	 //Price of the item
	 String fk_price = driver.findElement(By.xpath(config.getProperty("fp_price"))).getText();
	
	 String flipkart_price = fk_price.replaceAll("[₹,]", "");
	 System.out.println("Price of the Item in List : "+flipkart_price);
	
	 //Add to Cart
	 driver.findElement(By.xpath(config.getProperty("addtoCart"))).click();
	
	 //Price
	 String total_price = driver.findElement(By.xpath(config.getProperty("totalPrice"))).getText();
	 String flipkart_total_price = total_price.replaceAll("[₹,]", "");
	 int final_price = Integer.parseInt(flipkart_total_price);
	 System.out.println("Price of the Item in Cart : "+final_price);
	 
	 

	
	 System.out.println("*******AMAZON***********");
	 
	 
	 //Amazon
	  driver.navigate().to("https://www.amazon.in/");
		 
	  //Maximize the Chrome Window
	  driver.manage().window().maximize();
	  
	  
	 
	  
	  //Search field
	  driver.findElement(By.id(config.getProperty("amazon_search"))).sendKeys("mi tv");
	 
	 //search icon
	 driver.findElement(By.id(config.getProperty("amazon_search_icon"))).click();
	 
	 TimeUnit.SECONDS.sleep(10);
	 
	  //Select item
	 WebElement element = driver.findElement(By.xpath(config.getProperty("text")));

	 Actions actions = new Actions(driver);

	 actions.moveToElement(element).click().perform();
	 
	 TimeUnit.SECONDS.sleep(10);
	 
     
     //Print price
     String amazon_price=driver.findElement(By.xpath(config.getProperty("amazonPrice"))).getText();
    
     String ama_price = amazon_price.replaceAll("[₹,]", "");
     System.out.println("Price of the Item in List : "+ama_price);
     
     TimeUnit.SECONDS.sleep(10);

     
     //Add cart
     driver.findElement(By.name(config.getProperty("amazonToCart"))).click();
     TimeUnit.SECONDS.sleep(5);
     //Cart
     driver.findElement(By.xpath(config.getProperty("cartButton"))).click();
     TimeUnit.SECONDS.sleep(5);
     //cart price
     String amazon_cart_price=driver.findElement(By.xpath(config.getProperty("amazonCartPrice"))).getText();
     String cart_price = amazon_cart_price.replaceAll("[₹,]", "");
     double value = Double.parseDouble(cart_price);
     int amazon_final_price = (int)value;
	 System.out.println("Price of the Item in Cart : "+amazon_final_price);

     
	 
	 
	 //comparing
	 if(final_price > amazon_final_price )
	 {
		 System.out.println("Amazon Provides Cheaper");
	 }
	 else if(final_price < amazon_final_price)
	 {
		 System.out.println("Flipkart Provides Cheaper");
	 }
	 else
	 {
		 System.out.println("Both Prices are Same");
	 }
	 
     
  }
  @AfterTest()
  public void quitBrower()
  {
	  driver.quit();
  }
}
