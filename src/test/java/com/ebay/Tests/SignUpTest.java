package com.ebay.Tests;
import java.util.Objects;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ebay.base.Base;
import com.ebay.base.DriverFactory;
import com.ebay.pages.SignUpPage;

public class SignUpTest extends Base {
        
	 SignUpPage signup;
	 
	 public SignUpTest() {
		    
		 super();
	 }
	 
	 @BeforeSuite
	 public void SetUp() {
		     
		 Initialization();
		 
		 signup=new SignUpPage(DriverFactory.getDriver());
	 }
	 
	 
	 @Test
	 public void ClickSignUpButton() throws InterruptedException {
		    
		 signup.ClickOnSignUp_Btn();
		 
		 signup.ClickCaptcha();
	 }
	 
	 
	 
	 @AfterSuite
	 public void QuitDriver() {
		    
			if(Objects.nonNull(DriverFactory.getDriver())) {
				   
			        DriverFactory.getDriver().quit();
				
			}
	 }
	
}
