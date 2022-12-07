package taskScenario;

import java.util.ArrayList;
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
	  @BeforeTest
	  public void launchBrowser() throws InterruptedException
	  {
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
		  driver.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
		 
		  //Search Item
		 WebElement search =  driver.findElement(By.name("q"));
		 search.click();
		 search.sendKeys("mi tv");
		 WebElement search_icon = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[2]/div[2]/form/div/button"));
		 search_icon.click();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		
		 //Select Item
		 driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[3]/div[1]")).click();
		
		 TimeUnit.SECONDS.sleep(10);
		
		
		 //Switch Over Tabs
		 ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
	     driver.switchTo().window(newTb.get(1));
		
		 //Price of the item
		 String price = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/div[4]/div/label[1]/div[2]/div/div/div[2]")).getText();
		
		 String newStr = price.replaceAll("[₹]", "");
		 System.out.println("Price of the Item is : "+newStr);
		
		 //Add to Cart
		 driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")).click();
		
		 TimeUnit.SECONDS.sleep(10);
		
		 if(driver.findElements(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div/div[3]/div/div[3]/div[1]/div/button[2]")).size()>0)
		 {
			 System.out.println("Stock is Available");
			 
			 //Add quantity
			 driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div/div[3]/div/div[3]/div[1]/div/button[2]")).click();
			
			 TimeUnit.SECONDS.sleep(10);
				
			 
			 //Price
			 String total_price = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div/div/div/div[4]/div/span/div/div/span")).getText();
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
//		  driver.quit();
	  }
}
