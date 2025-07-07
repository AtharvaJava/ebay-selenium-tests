package com.ebay.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.TextTable.Cell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.ebay.base.Base;

public class Utils extends Base {

	 public static long Implicit_Wait=5;
	 
	 public static long Page_Load_Timeout=10;
	 
	 public static String TESTDATA_SHEET_PATH = "C:\\Users\\61073077\\eclipse-workspace\\Ebay\\src\\main\\java\\com\\ebay\\TestData\\TestData.xlsx";

		static Workbook book;
		static Sheet sheet;
	 
	 public static void takeScreenshotAtEndOfTest() throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		}

	 public static Object[][] getTestData(String sheetName) {
			FileInputStream file = null;
			try {
				file = new FileInputStream("C:\\Users\\61073077\\eclipse-workspace\\Ebay\\src\\test\\resources\\Locators.xlsx");
			} catch (FileNotFoundException e) {
			e.printStackTrace();
			}
			try {
			book = WorkbookFactory.create(file);			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
			}
			sheet = (Sheet) book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			// System.out.println(sheet.getLastRowNum() + "--------" +
			// sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
								for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					// System.out.println(data[i][k]);
				}
			}
			return data;
		}


}
