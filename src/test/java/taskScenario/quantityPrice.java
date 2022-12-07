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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class quantityPrice {
	
	public static WebDriver driver;
	public static Properties config;
	  @BeforeTest
	  public void launchBrowser() throws InterruptedException, IOException
	  {
		 
		  config=new Properties();
			FileInputStream input = new  FileInputStream("config.properties");
			config.load(input);
		  
		  WebDriverManager.chromedriver().setup();
	      ChromeOptions options = new ChromeOptions();
	      driver=new ChromeDriver(options);
		 
		
		 
		  driver.navigate().to("https://www.flipkart.com/");
		 
		  //Maximize the Chrome Window
		  driver.manage().window().maximize();
		 
		
	  }
	  @Test
	  public void senario_1() throws InterruptedException
	  {
		  
		  //login_cancel button
		  driver.findElement(By.xpath(config.getProperty("login_cancel_button"))).click();
		 
	
		  
		  //Search Item
		   driver.findElement(By.name(config.getProperty("search_field"))).sendKeys("mi tv");
		 
		   //Search Icon
		   driver.findElement(By.xpath(config.getProperty("search_icon"))).click();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		
		 //Select Item
		 driver.findElement(By.xpath(config.getProperty("select_item"))).click();
		
		 TimeUnit.SECONDS.sleep(10);
		
		
		 //Switch Over Tabs
		 ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
	     driver.switchTo().window(newTb.get(1));
		
		 //Price of the item
		 String price = driver.findElement(By.xpath(config.getProperty("item_price"))).getText();
		
		 String newStr = price.replaceAll("[₹]", "");
		 System.out.println("Price of the Item is : "+newStr);
		
		 //Add to Cart
		 driver.findElement(By.xpath(config.getProperty("addtoCart"))).click();
		
		 TimeUnit.SECONDS.sleep(10);
		
		 if(driver.findElements(By.xpath(config.getProperty("addquantityIcon"))).size()>0)
		 {
			 System.out.println("Stock is Available");
			 
			 //Add quantity
			 driver.findElement(By.xpath(config.getProperty("addquantityIcon"))).click();
			
			 TimeUnit.SECONDS.sleep(10);
				
			 
			 //Price
			 String total_price = driver.findElement(By.xpath(config.getProperty("price_value"))).getText();
			 String tot_price = total_price.replaceAll("[₹]", "");
			 int cart_price= Integer.parseInt(tot_price);
			 System.out.println("Total Price of the Items is : "+cart_price);
		 }
		 else
		 {
			 System.out.println("Out Of Stock");
		 }
		
		
		
	  }
	
	  @AfterTest()
	  public void quitBrowser()
	  {
		  driver.quit();
	  }
}
