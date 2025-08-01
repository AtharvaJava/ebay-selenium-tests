package com.ebay.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenshotUtil {
	
	

public static String captureFailedTestCaseScreenshot(WebDriver driver,String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
        

        String filePath = "FailedScreenshots/" + testName + "_" +timestamp + ".png";
        try {
            FileUtils.copyFile(srcFile, new File(filePath));
            System.out.println("Screenshot saved: " + filePath);
            
            return filePath;
            
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
            
            return null;
        }
    }



}




