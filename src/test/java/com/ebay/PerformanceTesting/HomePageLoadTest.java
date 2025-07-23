package com.ebay.PerformanceTesting;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ebay.base.Base;
import com.ebay.base.DriverFactory;

public class HomePageLoadTest extends Base{
	
	 WebDriver driver;

		   
	   @DataProvider (name="SearchItems", parallel=true)
	   public Object[][]SearchItems(){
		   
		   return new Object[][] {
			      
			   {"laptop"},{"smartphone"},{"headphones"},{"camera"}

		   };
	   }

	   @Test(dataProvider="SearchItems",timeOut=120000)
	   public void HomePagePerformanceTest(String SearchItems) throws InterruptedException {
		   
		   
		   try {

            WebDriver driver = new ChromeDriver(); // Create new driver per test
            DriverFactory.setDriver(driver);

            driver.get("https://www.ebay.com");
		   
		   System.out.println(Thread.currentThread().getName()+ "_Home Page Loaded");
		   
		   long startTime = System.currentTimeMillis();
		   
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
           WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gh-ac")));

           searchBox.clear();
           
           Thread.sleep(4000);
           searchBox.sendKeys(SearchItems);
           
           Thread.sleep(4000);
           WebElement SearchButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='gh-search-btn']")));
           SearchButton.click();
           
           wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".srp-results"))); 
           
          
           System.out.println(Thread.currentThread().getName() + " - Search executed");
           

           long endTime = System.currentTimeMillis();
           long duration = endTime - startTime;
           System.out.println(Thread.currentThread().getName() + " - Search for '" + SearchItems + "' took " + duration + " ms");
          
           LogtoCsv(SearchItems, String.valueOf(duration));
           
		   }catch (Exception e){

               System.err.println("Test failed for: " + SearchItems);
               e.printStackTrace();
   
		   }finally {
			   
			   DriverFactory.removeDriver();
		   }
     
           
		         
	   }
 
       
	   public synchronized void LogtoCsv(String SearchItems,String durations) {
		   

          String filePath = "Ebay/reports/PerformanceResultsHomePage.csv";
          File file = new File(filePath);
          file.getParentFile().mkdirs();
           boolean fileExists = file.exists();

		   try(PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
		   if (!fileExists) {
			   
               writer.println("SearchItem,Duration(ms)");
           }
           writer.println(SearchItems + "," + durations);

		   }catch(Exception e) {
			    
			   e.printStackTrace();
		   }
	   }

}
