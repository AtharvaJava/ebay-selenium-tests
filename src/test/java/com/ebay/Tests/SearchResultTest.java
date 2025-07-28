package com.ebay.Tests;

import java.util.Objects;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ebay.base.Base;
import com.ebay.base.DriverFactory;
import com.ebay.pages.SearchResult;
import com.ebay.pages.SignUpPage;
import com.ebay.utils.Utils;

public class SearchResultTest extends Base{

	 SearchResult searchResult;
	 SignUpPage signup;
	 Utils util;
	 
	 public SearchResultTest() {
		   
		  super();
	 }
	 
	 @BeforeSuite
	 public void SetUp() {
		 
		  Initialization();
		  
		  searchResult=new SearchResult(DriverFactory.getDriver());
	 }
	 
	 @Test (priority=1)
	 public void VerifySearchResultAfterEnteringText() throws InterruptedException, InvalidFormatException {
		 

          Object[][] testData = util.getTestData("Results");
         String searchItem = testData[0][0].toString();

		 
		 searchResult.EnterText(searchItem);
		 
	 }
	 
	 @Test (priority=2)
	 public void VerifySearchResultAfterEnteringInvalidText() throws InterruptedException {
		   
		  searchResult.EnterInvalidText();
	 }
	
	 
	 @Test (priority=3)
	 public void VerifySortingFunctionalityFromLowestToHighest() throws InterruptedException {
		    
		   searchResult.VerifySortingFromLowestToHighest();
	 }
	 
	 
	 @Test (priority=4)
	 public void VerifyPagination() throws InterruptedException{
     
		   searchResult.Pagination();
	 }
	 
	 
	 @AfterSuite
	 public void Quit() {
		   
		 if(Objects.nonNull(DriverFactory.getDriver())) {
			   
                    DriverFactory.getDriver().quit();
				
			}
	 }
}
