package com.ebay.base;

import org.openqa.selenium.WebDriver;

import com.epam.healenium.SelfHealingDriver;



public class DriverFactory {

	

private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

//	private static ThreadLocal<SelfHealingDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
    	  

    	 return driver.get();
    }
    
    
    public static void setDriver(WebDriver driverInstance) {
    	
//    	SelfHealingDriver healingDriver = SelfHealingDriver.create(driverInstance);
    	  driver.set(driverInstance);
    	
//    	driver.set(healingDriver);    	  
    	 
    }
    
    public static void removeDriver() {
    	
    	  if(driver.get()!=null) {
    		  
    		  driver.get().quit();
       	   
       	      driver.remove();	  
    	  }
    	 
    }

}