package com.ebay.PerformanceTesting;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.ebay.base.DriverFactory;
import com.ebay.pages.SearchResult;

public class SearchResultLoadTest {

	  WebDriver driver;
	 
	  
	  SearchResult searchResult;
	  long startTime;
	  
	  
	  
	  @Test
	  public void SearchResultPage() {
		    
		  try { 
			  
			    searchResult=new SearchResult(DriverFactory.getDriver());
			    
			    driver = new ChromeDriver(); // Create new driver per test
	            DriverFactory.setDriver(driver);

	            driver.get("https://www.ebay.com");
	            
	            driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("Laptop");
	            
	            Thread.sleep(3000);
	            
	            driver.findElement(By.xpath("//button[@id='gh-search-btn']")).click();
	            
	            Thread.sleep(4000);
	            
	            WebElement PaginationContainer=driver.findElement(By.xpath("//div[@class='s-pagination__container']"));
	            
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].scrollIntoView(true);", PaginationContainer);
	            
	            List<WebElement> PageLinks=driver.findElements(By.xpath("//ol[@class='pagination__items']//li//a"));
	            
	            for(int i=0;i<PageLinks.size();i++) {
	            	   
	            	    try {
	            	    	

                       long startTime = System.currentTimeMillis();
 
                       PageLinks.get(i).click();
                       
                       Thread.sleep(3000);
                      
                       driver.navigate().back();

                       Thread.sleep(2000);
                       

                      long endTime = System.currentTimeMillis();
                      long duration = endTime - startTime;

                      LogtoCsv_SearchResult(i + 1, String.valueOf(duration));

	            	    	
	            	    }catch(Exception e) {
	            	    	  
	            	    	e.printStackTrace();
	            	    }
	            }
	             
			  
		  }catch (Exception e){
			  
			    e.printStackTrace();
			    
		  }finally {
			    
			    DriverFactory.removeDriver();
		  }
		   
	  }
	  
	  public synchronized void LogtoCsv_SearchResult(int PaginationNumber ,String durations) {
		   

          String filePath = "Ebay/reports/PerformanceResult_SearchResultPage.csv";
          File file = new File(filePath);
          file.getParentFile().mkdirs();
           boolean fileExists = file.exists();

		   try(PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
		   if (!fileExists) {
			   
               writer.println("Pagination,Duration(ms)");
           }
           writer.println(PaginationNumber + "," + durations);

		   }catch(Exception e) {
			    
			   e.printStackTrace();
		   }
	   }
}
