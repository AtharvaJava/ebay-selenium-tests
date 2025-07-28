package com.ebay.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ebay.base.Base;
import com.ebay.utils.WebElementMethods;


import dev.failsafe.internal.util.Assert;


public class HomePage extends Base{

	
	 WebElementMethods method;
	 WebDriver driver;
	 
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	 
     @FindBy (xpath="//button[@class='gh-flyout__target']//span[text()='Shop by category']")
     WebElement ShopByCategory;
     
     @FindBy (xpath="//a[text()='Parts & accessories']")
     WebElement PartAccessories;
     
     @FindBy (xpath="//div[@class='seo-expandable-list seoel']//a")
     WebElement Items;
     
     @FindBy (xpath="//a[@class='gh-logo']")
     WebElement Ebay_Logo;
     
     @FindBy (xpath="//a[text()='Daily Deals']")
     WebElement DailyDeals;
     
     @FindBy (xpath="//a[text()='Gift Cards']")
     WebElement GiftCard;
     
     @FindBy (xpath="//div[@id='vl-flyout-nav']//li//a")
     List<WebElement> HomePagesLinks;
     
     @FindBy (xpath="//h2[text()='Car & Truck Parts']")
     WebElement CarTruckParts;
     
     @FindBy (xpath="//input[@id='gh-ac']")
     WebElement Search_TextBox; 
     
     @FindBy (xpath="//select[@id='gh-cat']")
     WebElement AllCategories_Dropdown;
     
     @FindBy (xpath="//button[@id='gh-search-btn']")
     WebElement SearchButton;
     
     @FindBy (xpath="//span[@id='nid-q0p-4']//button[contains(@class,'fake-menu-button__button')]")
     WebElement BuyingFormat_Option;
     
     @FindBy (xpath="//ul[@class='fake-menu__items']//li//a[@class='fake-menu-button__item']")
     List<WebElement> BuyingFormat_Options;
     
     @FindBy (xpath="//h2[text()='eBay Motors']")
     WebElement EbayMotors_Heading;
    
     
     @FindBy (css=".srp-results")
     WebElement SearchResult;
     
     @FindBy (xpath="//ul[@class='vl-flyout-nav__container']//li//a")
     List<WebElement> TopNavigation_Menus;
     
     @FindBy (xpath="//h1[text()='Electronics']")
     WebElement AfterRedirection_Linkheading;
     
     @FindBy (xpath="//span[@class='gh-cart__icon']")
     WebElement CartIcon;
     
     @FindBy (xpath="//div[@class='gh-notifications']//button[@class='gh-flyout__target']")
     WebElement Notification_Icon;
     
     @FindBy (xpath="//a[text()='sign-in']")
     WebElement SignIn_Option;
     
     @FindBy (xpath="//a[@class='gh-categories__cat']//h3")
     List<WebElement> ShopByCategories_Headings;
     

    
     public HomePage(WebDriver driver) {
    	        
    	   this.driver=driver;
    	   PageFactory.initElements(driver,this);
    	   this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	   this.method = new WebElementMethods(); 
    	   
     }
     
     //Verify EbayLogo is displayed or not
     public void isEbayLogoDisplayed() {
    	     
    	  method.IsDisplayed(driver, Ebay_Logo);
     } 
     
     
     //Verify Search Input Field is Displayed or not
     public void isSearchInputFieldDisplayed() {
    	    
    	   method.IsDisplayed(driver, Search_TextBox);
    	   method.IsEnabled(driver, Search_TextBox);
     }
     
     
     //Verify after clicking on search icon without entering the text whether it should navigate to EbayMotos Page or not
     public String ClickSearchIconWithoutEnteringText() throws InterruptedException {
    	   
    	 method.ClickOnWebElement(driver, SearchButton);
    	   	 
    	 Thread.sleep(3000);
    	 
    	 String ActualText=EbayMotors_Heading.getText();
    	 
    	 return ActualText;
     	 
     }
     
     //Verify After Entering KeyWord Whether search results are displaying or not
     public void EnterKeywordInSearchTextBox(String Text) throws InterruptedException {
    	   
    	    method.EnteringText(driver, Search_TextBox, Text);
    	    
    	    Thread.sleep(4000);
    	    
    	    method.ClickOnWebElement(driver, SearchButton);
    	    
    	    Thread.sleep(6000);
    	    
    	   if(method.IsDisplayed(driver, SearchResult)==true) {
    		     
    		      System.out.println("Element is Present");
    	   }else {
    		     
    		    System.out.println("Element is not present");
    	   }

           // Use JavaScript Executor to click the element
           JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript("arguments[0].click();", Ebay_Logo);
    	  
    	   
    	   Thread.sleep(3000);
    	   
    	  
     }
     
     //Verify Top Navigation Menus
     public boolean TopNavigation_Menus() throws InterruptedException {
    	      
    	 for(WebElement el:TopNavigation_Menus) {
    		    
    		     if(el.getText().equals("Motors")) {
    		    	      	   
    		    	  return true;
    		     }
    		     
    	 }
    	 return false;  
     }
     
     //Verify In top Navigation bar whether the links is correctly redirects or not
     public boolean VerifyLink_Redirection() throws InterruptedException {
    	 
    	 try {
    	 
    	 for(WebElement el:TopNavigation_Menus) {
    		 
    		 
    		 
    		 
    		 
    		    String linkText=el.getText().trim();
    		    
    		    if (linkText.equalsIgnoreCase("Electronics"))  {
    		    	  
    		    	 
    		    		 
    		    		 JavascriptExecutor js = (JavascriptExecutor) driver;
    		             js.executeScript("arguments[0].click();", el);

                      
                      wait.until(ExpectedConditions.titleContains("Electronics"));
                      

//                      String currentUrl = driver.getCurrentUrl();
                      String pageTitle = method.GetTitle(driver);
                      
                      
                      if(pageTitle.contains("Electronics")) {
                    	    
                    	      System.out.println("Successfully Redirects to Electronics Page");
                    	      return true;
                      
                      }else {
                    	   
                    	      System.out.println("Electronics link not found in top navigation bar");
                    	      return false;
                      }

    		    	    		    	  
    		    	  
    		    	  }
    		    		
    		    	  }
    		    	
    		    	 
    		    }catch (TimeoutException e){
		    		  
		    		  System.out.println("Timed out waiting for the Electronics heading to appear.");
		    		  return false;
    	 
    		    }
    	 
    		    return false;
     }
     


     
     //Verify Whether the cart icon is visible as well as clickable or not
     public boolean VerifyCart_Icon() throws InterruptedException {
    	 
         
                  if(method.IsDisplayed(driver, CartIcon)) {
                	  
                	  method.IsEnabled(driver, CartIcon);
                	  
                	  return true;
                	  
                  }
                  
     	 return false;
    
     }
     
     
     //Verify Notification Icon
     public boolean VerifyNotificationIcon() throws InterruptedException {
    	   
    	     if(method.IsDisplayed(driver, Notification_Icon)) {
    	    	    
    	    	     Actions action=new Actions(driver);
    	    	     action.moveToElement(Notification_Icon).build().perform();
    	    	     
    	    	     Thread.sleep(2000);
    	    	     
    	    	     return true;
    	    	     
    	     }
    	     
    	     return false;
     }
     
     
     //Verify Footer Links
     public void VerifyFooterLinks() {
    	 
    	//Clicking on Ebay Logo to navigate to home page
		  JavascriptExecutor js = (JavascriptExecutor) driver;
          js.executeScript("arguments[0].click();", Ebay_Logo);
    	 
    	   
    	   WebElement footer=driver.findElement(By.xpath("//footer[@class='gh-footer is-large']"));
    	   
    	   method.scrollToElement(driver, footer);
    	 
    	   List<WebElement> list=driver.findElements(By.tagName("a"));
    	   
    	   if(list.isEmpty()) {
    		   
    		     System.out.println("No Footer Links found on ebay");
    	   
    	   }else {
    		   
    		     for(WebElement link:list) {
    		    	    
    		    	      String LinkText=link.getText().trim();
    		    	      String href=link.getAttribute("href");
    		    	      
    		    	      if(link.isDisplayed() && link.isEnabled()) {
    		    	    	    
    		    	    	     System.out.println("Link Available "+LinkText+ "->" +href);
    		    	    	     
    		    	    	     break;
    		    	    	     
    		    	    	     
    		    	      }else {
    		    	    	  
    		    	    	    System.out.println("No Link Available");
    		    	      }
    		     }
    	   }
    	     
     }
     
     
     //Click on Shop By Category Section
     public void Click_ShopByCategory() throws InterruptedException {
    	     
         	 
    	  method.ClickOnWebElement(driver,ShopByCategory);
    	  
    	  Thread.sleep(2000);
     }
     
     //Verify Shop By category Headings
     public void VerifyShopByCategory_Headings() {
    	    
    	    String arr[]= {"Motors" , "Clothing & Accessories" ,"Sporting goods" ,"Electronics" ,"Business & Industrial" ,"Jewelry & Watches" ,"Collectibles & Art", "Home & garden" ,"Other categories"};
    	    
    	    for(WebElement headings: ShopByCategories_Headings) {
    	    	   
    	    	   if(arr.length==ShopByCategories_Headings.size()) {
    	    		   
    	    		      System.out.println("Counts are matachings!");
    	    	   }     
    	    		       else {
    	    		   
    	    		   System.out.println("Count mismatch! Expected: " + arr.length + ", Found: " + ShopByCategories_Headings.size());
    	    		   
    	    	   }
    	    	  
    	    	   



    	    }
     }
     
     public void Click_PartAccessories() {

    	 
    	 WebElement element = driver.findElement(By.xpath("//a[text()='Parts & accessories']"));

         // Use JavaScript Executor to click the element
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].click();", element);

    	
     }
     
     public void SearchTextBox() throws InterruptedException {
    	   
    	   Search_TextBox.sendKeys("Mobile Phones");
    	   
    	   Thread.sleep(3000);
    	   
    	   Select select=new Select(AllCategories_Dropdown);
    	  
    	   select.selectByVisibleText("Books");
    	   
    	   Thread.sleep(2000);
    	   
    	   if(SearchButton.isDisplayed()) {
    		   
    		   SearchButton.click();
        	   
        	   Thread.sleep(2000);
        	   
    	   }
    	   
     }
     
   
   
       public void DailyDealsLink() throws InterruptedException {
    	      
    	 method.ClickOnWebElement(driver,DailyDeals);
    	 
    	 Thread.sleep(2000);
    	 
    	 driver.navigate().back();
     }
     
     public void GiftCardLink() throws InterruptedException {
    	     
//    	 GiftCard.click();
    	 
    	 method.ClickOnWebElement(driver,GiftCard);
    		 
    	 Thread.sleep(3000);
     }
     
     public void HomePages_Links() throws InterruptedException {
    	     
   	    for(WebElement Link : HomePagesLinks) {
   	    	       
   	    	 System.out.println(Link.getText());
   	    	 
   	    	 if(Link.getText().equals("Motors")) {
    	    		        
   	    		    
   	    		 method.ClickOnWebElement(driver, Link);
   	    		    		
 	    	 }
    }
    	     	 
    	    }
     
    
}
