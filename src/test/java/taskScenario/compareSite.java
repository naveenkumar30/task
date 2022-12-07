package taskScenario;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class compareSite {
	
	
	public static WebDriver driver;
	
	@BeforeTest
	public void launchBrowser()
	{
		 WebDriverManager.chromedriver().setup();
	      ChromeOptions options = new ChromeOptions();
	      driver=new ChromeDriver(options);
		 
	}

  @Test
  public void amazon() throws InterruptedException 
  {
	  
	  
	  //Flipkart
	  driver.navigate().to("https://www.flipkart.com/");
		 
	  //Maximize the Chrome Window
	  driver.manage().window().maximize();
	 
	  //login_cancel button
	  driver.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
	 
	  //Search Item
	 WebElement flipkart_search =  driver.findElement(By.name("q"));
	 flipkart_search.click();
	 flipkart_search.sendKeys("mi tv");
	 WebElement flipkart_search_icon = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[2]/div[2]/form/div/button"));
	 flipkart_search_icon.click();
	
	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	
	 //Select Item
	 driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[3]/div[1]")).click();
	
	 TimeUnit.SECONDS.sleep(10);
	
	
	 //Switch Over Tabs
	 ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
     driver.switchTo().window(newTb.get(1));
     
	 System.out.println("*******FLIPKART***********");

	
	 //Price of the item
	 String fk_price = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/div[4]/div/label[1]/div[2]/div/div/div[2]")).getText();
	
	 String flipkart_price = fk_price.replaceAll("[₹,]", "");
	 System.out.println("Price of the Item in List : "+flipkart_price);
	
	 //Add to Cart
	 driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")).click();
	
	 //Price
	 String total_price = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div/div/div/div[4]/div/span/div/div/span")).getText();
	 String flipkart_total_price = total_price.replaceAll("[₹,]", "");
	 int final_price = Integer.parseInt(flipkart_total_price);
	 System.out.println("Price of the Item in Cart : "+final_price);
	 
	 

	
	 System.out.println("*******AMAZON***********");
	 
	 
	 //Amazon
	  driver.navigate().to("https://www.amazon.in/");
		 
	  //Maximize the Chrome Window
	  driver.manage().window().maximize();
	  
	  
	 
	  
	  //Search field
	 WebElement amazon_search= driver.findElement(By.id("twotabsearchtextbox"));
	 amazon_search.click();
	 amazon_search.sendKeys("mi tv");
	 
	 //search icon
	 driver.findElement(By.id("nav-search-submit-button")).click();
	 
	 TimeUnit.SECONDS.sleep(10);
	 
	  //Select item
	 WebElement element = driver.findElement(By.xpath("//*[contains(text(),'MI 80 cm (32 inches) 5A Series HD Ready Smart Android LED TV L32M7-5AIN (Black)')]"));

	 Actions actions = new Actions(driver);

	 actions.moveToElement(element).click().perform();
//	 driver.findElement(By.xpath("//*[contains(text(),'MI 80 cm (32 inches) 5A Series HD Ready Smart Android LED TV L32M7-5AIN (Black)')]")).click();
	 
	 TimeUnit.SECONDS.sleep(10);
	 
     
     //Print price
     String amazon_price=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[5]/div[4]/div[4]/div[10]/div[3]/div[1]/span[2]/span[2]/span[2]")).getText();
    
     String ama_price = amazon_price.replaceAll("[₹,]", "");
     System.out.println("Price of the Item in List : "+ama_price);
     
     TimeUnit.SECONDS.sleep(10);

     
     //Add cart
     driver.findElement(By.name("submit.add-to-cart")).click();
     TimeUnit.SECONDS.sleep(5);
     //Cart
     driver.findElement(By.xpath("//*[@id=\"attach-sidesheet-view-cart-button\"]/span/input")).click();
     TimeUnit.SECONDS.sleep(5);
     //cart price
     String amazon_cart_price=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[4]/div/div[1]/div[1]/div/form/div/div[3]/div[1]/span[2]/span")).getText();
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
}
