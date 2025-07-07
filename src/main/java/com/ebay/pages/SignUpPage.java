package com.ebay.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ebay.base.Base;

public class SignUpPage extends Base {
       
	@FindBy (xpath="//div[@class='gh-notifications']//button[@class='gh-flyout__target']")
	WebElement Notification_Icon;
	
	@FindBy (xpath="//a[text()='sign-in']")
	WebElement SignIn;
	
	@FindBy (xpath="//*[@id='anchor-td']")
	WebElement Captcha_CheckBox;
	
	@FindBy (xpath="//div[@class='button-submit button']")
    WebElement Skip_Btn;
	
	
	public SignUpPage(WebDriver driver) {
		     
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void ClickOnSignUp_Btn() {
		    
		Actions action=new Actions(driver);
		action.moveToElement(Notification_Icon).click(SignIn).build().perform();
	}
	
	public void ClickCaptcha() throws InterruptedException {

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Widget containing checkbox ')]")));
		Thread.sleep(2000); 
		Captcha_CheckBox.click();
		Thread.sleep(2000); 
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Main content of the hCaptcha challenge']")));
		Thread.sleep(2000);
		
		Skip_Btn.click();
		Thread.sleep(3000);
		
		
		
	}
}
