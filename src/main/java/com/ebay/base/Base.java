package com.ebay.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.ebay.utils.Utils;

public class Base {

	 protected static WebDriver driver;
	 protected Properties prop;
	 
	   public Base(){
		   
		   prop=new Properties();
		   
		   try {
			FileInputStream ip=new FileInputStream("C:\\Users\\61073077\\eclipse-workspace\\Ebay\\src\\main\\java\\com\\ebay\\config\\config.properties");
		    try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		   } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		       
		    
	   }
	   
	   
	   
	   public void Initialization() {
		         
		       String browserName=prop.getProperty("browser");
		       
		       if(browserName.equals("Chrome") && Objects.isNull(DriverFactory.getDriver())) {
		    	      
                   System.setProperty("webdriver.chrome.driver", "C:\\Users\\61073077\\OneDrive - LTIMindtree\\Pictures\\chromedriver-win64\\chromedriver.exe");
			       ChromeOptions options = new ChromeOptions();
		           options.addArguments("--remote-allow-origins=*");
//		           driver = new ChromeDriver(options);
		           options.setPageLoadStrategy(PageLoadStrategy.NONE);
                   WebDriver driverInstance = new ChromeDriver(options);
                   DriverFactory.setDriver(driverInstance);
                   driverInstance.manage().window().maximize();
    		       
                   driverInstance.get("https://www.ebay.com/");
    		  
                   driverInstance.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
                   
                   driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    		       
                   driverInstance.manage().deleteAllCookies();
    		      
    		       
                   
    		       
                  
    		       
		       }
		      
//		       WebDriver driver = DriverFactory.getDriver();
		       
		       
	   }
}
