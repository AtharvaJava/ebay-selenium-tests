package com.ebay.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ebay.base.Base;

public class WebElementMethods extends Base implements ActionInterface{

	
	//Scroll Into View Method
	public void ScrollIntoView(WebDriver driver,WebElement el) {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", el);
		
	}
	
	//Click on WebElement
	public boolean ClickOnWebElement(WebDriver driver,WebElement el) {
		 		
		boolean flag=false;
		
		if(el.isDisplayed()) {
			  
			
			  el.click();
			
			 flag=true;
			 
			 if(flag) {
				   
				 System.out.println("Element Clicked Successfully");
			
			}else {
				  
				 System.out.println("Element is not clickable");
			}
			
		}
		
		return false;
		
	}
	
	//Find an Element
	public boolean FindElement(WebDriver driver,WebElement el) {
		 
		boolean flag=false;
		
		try {
			
		   el.isDisplayed();
		   flag=true;
			
		}catch(Exception e) {
			  
			flag=false;
			
		}finally {
			  
			   if(flag) {
				       
				   System.out.println("Element Found Successfully");
			   }
			   else {
				     
				    System.out.println("Element Not Found Successfully");
			   }
			   
			   
		}
		
		return flag;
		
	}
	
    //Check if Element is displayed 
    public boolean IsDisplayed(WebDriver driver, WebElement el) {
     
    	 boolean flag=false;
    	 flag=FindElement(driver,el);
    	 
    	 if(flag) {
    		   
    		  el.isDisplayed();
    		  
    		  return true;
//    		 
    	 }
    	 
    	 
    	 
    	 return false;
    }
	


	
	//Check If Element is Enabled 
	public boolean IsEnabled(WebDriver driver, WebElement el) {
		  
		 boolean flag=false;
		 flag=FindElement(driver,el);
		 
		 
		 if(flag) {
			    
			 flag=el.isEnabled();
			 
			 return true;
		 }
		 
		 return false;
	}

	
	//Enter Text in TextBox
	public void EnteringText(WebDriver driver,WebElement el,String Text) {
		   
		  boolean flag=false;
		  flag=FindElement(driver,el);
		  
		  if(flag) {
			     
			     el.sendKeys(Text);
			     flag=true;     
		  }else {
			    
			   System.out.println("Not able to enter the text");
		  }
		  
	}
    
	//Scrolling to the particular Element

    public void scrollToElement(WebDriver driver, WebElement element) {    
         
    	JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }
    
    //Scroll to bottom of the page
    public void ScrollToBottomOfPage(WebDriver driver, WebElement element) {
    	  
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

	
    //Get Title
    public String GetTitle(WebDriver driver) {
    	    
    	return driver.getTitle();
    	 
    }
	  
}
