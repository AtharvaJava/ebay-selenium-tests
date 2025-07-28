package com.ebay.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ebay.utils.WebElementMethods;

public class SearchResult {

	WebDriver driver;
	WebElementMethods method;
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	
	@FindBy (xpath="//input[@id='gh-ac']")
    WebElement SearchTextBox; 
	
	@FindBy (xpath="//button[@id='gh-search-btn']")
    WebElement SearchButton;
	
	@FindBy (xpath="//ul[@id='ebay-autocomplete']")
	WebElement SearchResults;
	
	@FindBy (xpath="//div[@class='s-pagination__container']")
	WebElement Pagination;

	@FindBy (xpath="//ol[@class='pagination__items']//li//a")
	List<WebElement> Pagination_Numbers;
	
	@FindBy (xpath="//span[text()='eBay Refurbished']")
	WebElement EbayRefurbishedTag;
	
	@FindBy (xpath="(//div[@class='x-refine__price']//div//ul//li)[1]")
	WebElement SortingBy_LowestPrice;
	
	@FindBy (xpath="//div[@class='x-refine__price']")
	WebElement PriceSection;
	
	@FindBy (xpath="//div[@id='x-refine__group_1__0']//ul//li")
	List<WebElement> ScreenSize_CheckBox;
	
	@FindBy (xpath="(//div[@class='x-refine__price']//div//ul//li)[3]")
	WebElement SortingBy_HighestPrice;
	
	@FindBy (xpath="//span[@class='s-item__watchheart on-image s-item__watchheart--watch']//a")
	List<WebElement> LikeButton;
	
	
	public SearchResult(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  	   this.method = new WebElementMethods(); 
	}
	 
	//Verify After Entering Valid Text in TextBox whether search result displayed or not
	public void EnterText(String searchItem) throws InterruptedException {
		
		method.EnteringText(driver, SearchTextBox, searchItem);
		
		Thread.sleep(3000);
		
		method.ClickOnWebElement(driver, SearchTextBox);
		
		method.IsDisplayed(driver, SearchResults);
		
		Thread.sleep(6000);
	
	}

	
	//Verify After Entering Invalid Text in TextBox whether search result displayed or not
		public void EnterInvalidText() throws InterruptedException {
			
			
			
			SearchTextBox.clear();
			
			method.EnteringText(driver, SearchTextBox, "!@#455556&*()*");
			
			Thread.sleep(3000);
			
			method.ClickOnWebElement(driver, SearchTextBox);
			
			method.IsDisplayed(driver, SearchResults);
			
			Thread.sleep(6000);
		    
		}
		
   //Verify that pagination works correctly or not
      public void Pagination() throws InterruptedException {
    	  
    	  
    	   SearchTextBox.clear();
    	  
    	   method.EnteringText(driver, SearchTextBox, "Laptop");
    	   
    	   method.ClickOnWebElement(driver, SearchButton);
    	   
    	   Thread.sleep(3000);
    	   
    	   method.scrollToElement(driver, Pagination);
    	   
    	   Thread.sleep(3000);
    	   
    	   for(WebElement number:Pagination_Numbers) {
    		      
    		       method.ClickOnWebElement(driver, number);
    		       
    		       Thread.sleep(4000);
    		       
    		       method.IsDisplayed(driver, EbayRefurbishedTag);
    		       
    		       driver.navigate().back();
    		       
    		       Thread.sleep(5000);
    		       
    		       
    		       
    	   }
    	   
      }
      
 
      
      //Verify Sorting functionality by price from Lowest to Highest
      public void VerifySortingFromLowestToHighest() throws InterruptedException {
    	  
          SearchTextBox.clear();
    	  
   	      method.EnteringText(driver, SearchTextBox, "Laptop");
   	   
   	      method.ClickOnWebElement(driver, SearchButton);
   	      
   	      method.scrollToElement(driver, PriceSection);
    	  
    	  method.ClickOnWebElement(driver, SortingBy_LowestPrice);
    	  
    	  Thread.sleep(4000);
    	  
    	 
    	  
      }
      
     
      //Verify Sorting functionality by price from Highest to Lowest
      public void VerifySortingHighestToLowest() throws InterruptedException {
    	       
          SearchTextBox.clear();
    	  
   	      method.EnteringText(driver, SearchTextBox, "Laptop");
   	   
   	      method.ClickOnWebElement(driver, SearchButton);
   	      
   	      method.scrollToElement(driver, PriceSection);
   	      
   	      method.ClickOnWebElement(driver, SortingBy_HighestPrice);
   	      
   	      Thread.sleep(4000);
   	      
      }
      
      //Click on Like Button
      public void SelectLikeButton() throws InterruptedException {
    	  
    	    for(int i=0;i<LikeButton.size();i++) {
    	    	   
    	    	LikeButton.get(i).click();
    	    	
    	    	Thread.sleep(4000);
    	    }
    	     
    	  
      }
      
    
}
