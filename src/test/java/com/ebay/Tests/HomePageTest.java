package com.ebay.Tests;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ebay.base.Base;
import com.ebay.base.DriverFactory;
import com.ebay.pages.HomePage;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.util.Asserts;

public class HomePageTest extends Base {
	
	HomePage hp;
	

	public HomePageTest() {
		  
		super();
	}
	
	@BeforeSuite
	public void SetUp() {
		
		 Initialization();
		 
		 hp=new HomePage(DriverFactory.getDriver());
	}
	
	
	
	@Test(priority=1)
	public void TestIsEbayLogoDisplayed() throws InterruptedException {
		    
		  hp.isEbayLogoDisplayed();
		  
		  Thread.sleep(2000);
	}
	
	@Test (priority=2)
	public void TestIsSearchInputFieldDisplayed() {
		   
		   hp.isSearchInputFieldDisplayed();
		   
		   
	}
	
	@Test (priority=3)
	public void TestWithoutEnterTextClickOnSearchIcon() throws InterruptedException {
		    
		String Text=hp.ClickSearchIconWithoutEnteringText();
		
		Assert.assertEquals(Text, "eBay Motors");
		
	}
	
	@Test (priority=4)
	public void CheckResultDisplayingAfterEnteringText() throws InterruptedException {
		  
		  hp.EnterKeywordInSearchTextBox("Iphone");		  		  
	}
	
    @Test (priority=5)
    public void CheckTopNavigationBarLinks() throws InterruptedException {
    	   
    	   Assert.assertTrue(hp.TopNavigation_Menus(),"Link is present in top navigation bar");
    }
    
    
    @Test (priority=6)
    public void TestLinkCorrectlyRedirecting() throws InterruptedException {
    	   
    	  Assert.assertTrue(hp.VerifyLink_Redirection(),"Link is not working ");
    	  
    	
    	  
    }
    
    
    @Test (priority=7)
    public void CheckSignInOption() throws InterruptedException {
    	  
    	 Assert.assertTrue(hp.VerifyNotificationIcon());
    }
    
    
    @Test (priority=8)
    public void CheckCartIcon() throws InterruptedException {
    	  
    	 Assert.assertTrue(hp.VerifyCart_Icon(), "Cart Icon is not Displaying");
    }
    
    @Test (priority=10)
    public void TestFooterLinks() throws InterruptedException {
    	  
    	 hp.VerifyFooterLinks();
    	 
    	 Thread.sleep(3000);
    }
    
    @Test (priority=9)
    public void TestShopByCategories_Headings() throws InterruptedException {
    	   
    	  hp.Click_ShopByCategory();
    	  
    	  Thread.sleep(4000);
    	  
    	  hp.VerifyShopByCategory_Headings();
    }
    
//	@Test (priority=2)
//	public void ClickShopByCategory() throws InterruptedException {
//		      
//		hp.Click_ShopByCategory();
//		
//		Thread.sleep(3000);
//	}
	
//	@Test(priority=3)
//	public void ClickAccesorriesPart() throws InterruptedException {
//		    
//		hp.Click_PartAccessories();
//		
//		Thread.sleep(3000);
//		
//	}
	
//	@Test (priority=4)
//	public void ClickDailyDeals() throws InterruptedException {
//		    
//		 hp.DailyDealsLink();
//		 
//		 Thread.sleep(3000);
//	}
////	
//	@Test(priority=5)
//	public void ClickGiftCard() throws InterruptedException {
//		    
//		hp.GiftCardLink();
//		
//		Thread.sleep(2000);
//		
//	}
//	
//	@Test(priority=6)
//	public void CheckHomePageLinkText() throws InterruptedException {
//		     
//		 hp.HomePages_Links();
//		 
//		 Thread.sleep(2000);
//	}
//	
//	@Test (priority=7)
//	public void CheckSearchTextBox() throws InterruptedException {
//		  
//		hp.SearchTextBox();
//		
//		Thread.sleep(2000);
//	}

		
	@AfterSuite
	public void Quit() {
		
		if(Objects.nonNull(DriverFactory.getDriver())) {
		   
			DriverFactory.getDriver().quit();
			
			
		}
		
	}

}
