package com.ebay.Listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ebay.base.Base;
import com.ebay.base.DriverFactory;
import com.ebay.utils.ScreenshotUtil;

public class TestListner implements ITestListener{
	

    @Override
    public void onTestFailure(ITestResult result) {
        ScreenshotUtil.captureFailedTestCaseScreenshot(DriverFactory.getDriver(), result.getName());
    }


}
