package com.ebay.base;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	

private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();



    public static WebDriver getDriver() {
    	  


//               if (driver.get() == null) {
//                  
//            	   throw new IllegalStateException("WebDriver instance is null. It may have been quit already.");
//               
//               }

    	 return driver.get();
    }
    
    
    public static void setDriver(WebDriver driverInstance) {
    	
    	
    	  driver.set(driverInstance);
    	  
    	 
    }
    
    public static void removeDriver() {
    	
    	  if(driver.get()!=null) {
    		  
    		  driver.get().quit();
       	   
       	      driver.remove();	  
    	  }
    	 
    }

}