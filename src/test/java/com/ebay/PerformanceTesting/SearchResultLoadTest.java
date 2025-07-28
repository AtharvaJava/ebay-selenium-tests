package com.ebay.PerformanceTesting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
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
	 
	  private static final Map<Long, Long> searchDurations = new HashMap<>();


	     private static final String timestamp = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
		  
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
                       
                       Thread.sleep(4000);
                      
                       driver.navigate().back();

                       Thread.sleep(5000);
                       

                      long endTime = System.currentTimeMillis();
                      long duration = endTime - startTime;

                      searchDurations.put((long) (i+1), duration);
                      LogtoCsv_SearchResult(i + 1, String.valueOf(duration));

	            	    	
	            	    }catch(Exception e) {
	            	    	  
	            	    	e.printStackTrace();
	            	    }
	            }
	             
			  
		  }catch (Exception e){
			  
			    e.printStackTrace();
			    
		  }finally {
			    
			    DriverFactory.removeDriver();
			    
			    synchronized(SearchResultLoadTest.class) {
			    	
			    	 generateSummaryStatistics();
			         generateBarChart();
			    }
		  }
		   
	  }
	  
	  public synchronized void LogtoCsv_SearchResult(int PaginationNumber ,String durations) {
		  
		  
		  String timestamp = new SimpleDateFormat("dd_MM_yyyy").format(new Date()); 

          String filePath = "Ebay/reports/PerformanceResult_SearchResultPage.csv"+timestamp;
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
	  
	  private void generateSummaryStatistics() {
	       DescriptiveStatistics stats = new DescriptiveStatistics();
	       for (long duration : searchDurations.values()) {
	           stats.addValue(duration);
	       }

	       System.out.println("\n--- Summary Statistics ---");
	       System.out.println("Mean: " + stats.getMean());
	       System.out.println("Min: " + stats.getMin());
	       System.out.println("Max: " + stats.getMax());
	       System.out.println("Std Dev: " + stats.getStandardDeviation());
	   }

	   private void generateBarChart() {

       DefaultCategoryDataset dataset = new DefaultCategoryDataset();

       for (Entry<Long, Long> entry : searchDurations.entrySet()) {
           
    	   dataset.addValue(entry.getValue(), "Duration", String.valueOf(entry.getKey()));
       }

    JFreeChart chart = ChartFactory.createBarChart(
        "Search Duration per Pagination",
        "Pagination Number",
        "Duration (ms)",
        dataset
    );

    try {
        File chartFile = new File("Ebay/reports/SearchDurationChart_" + timestamp + ".png");
        ChartUtils.saveChartAsPNG(chartFile, chart, 800, 600);
        System.out.println("Chart saved to: " + chartFile.getAbsolutePath());
    } catch (IOException e) {
        e.printStackTrace();
    }
	   }
}
