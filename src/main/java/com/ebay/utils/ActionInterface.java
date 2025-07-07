package com.ebay.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ActionInterface {
	
	
	public void ScrollIntoView(WebDriver driver,WebElement el);
	public boolean ClickOnWebElement(WebDriver driver,WebElement el);
	public boolean FindElement(WebDriver driver,WebElement el);
	public boolean IsDisplayed(WebDriver driver, WebElement el);
	public boolean IsEnabled(WebDriver driver, WebElement el);
	
	

}
