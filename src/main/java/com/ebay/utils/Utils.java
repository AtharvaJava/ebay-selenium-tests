package com.ebay.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.TextTable.Cell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

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

//		static Workbook book;
//		static Sheet sheet;
	 
	 public static void takeScreenshotAtEndOfTest() throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		}

	 public static Object[][] getTestData(String sheetName) throws InvalidFormatException {


      Workbook book = null;
    Sheet sheet = null;

    
        FileInputStream file = null;
        try {
            file = new FileInputStream("C:\\Users\\61073077\\eclipse-workspace\\Ebay\\src\\test\\resources\\TestCase_Results.xlsx");
            book = WorkbookFactory.create(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);
   
       
       Row row = sheet.getRow(4);
       String cell = row.getCell(2).getStringCellValue();
        
       System.out.println(cell);

       
       Object[][] data = new Object[1][1];
       data[0][0] = cell;
        
       
       return data;
        



        
    }
         
	 

}
