package com.ebay.PerformanceTesting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;

import java.util.Map;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
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


private static final Map<String, Long> searchDurations = new HashMap<>();


     private static final String timestamp = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
	  

private static int completedTests = 0;
private static final int TOTAL_TESTS = 4; 

	  
	  
	   @DataProvider (name="SearchItems", parallel=true)
	   public Object[][]SearchItems(){
		   
		   return new Object[][] {
			      
			   {"laptop"},{"smartphone"},{"headphones"},{"camera"}

		   };
	   }

	   @Test(dataProvider="SearchItems",timeOut=120000 )
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
         

          searchDurations.put(SearchItems, duration);

          LogtoCsv(SearchItems, String.valueOf(duration));
          
		   }catch (Exception e){

              System.err.println("Test failed for: " + SearchItems);
              e.printStackTrace();
  
		   }finally {
			   
			   DriverFactory.removeDriver();
			   



synchronized (HomePageLoadTest.class) {
   completedTests++;
   if (completedTests == TOTAL_TESTS) {
       generateSummaryStatistics();
       generateBarChart();
   }
}

		   }
          
	   }

	   public synchronized void LogtoCsv(String SearchItems,String durations) {
		   
	
		   String timestamp = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
		   
         String filePath = "Ebay/reports/PerformanceResultsHomePage.csv_"+timestamp;
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
       

for (Map.Entry<String, Long> entry : searchDurations.entrySet()) {
    dataset.addValue(entry.getValue(), "Duration", entry.getKey());
}


       JFreeChart chart = ChartFactory.createBarChart(
           "Search Duration per Item",
           "Search Item",
           "Duration(ms)",
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