package com.sit.anchal.keyword.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.anchal.keyword2.Base;

public class KeywordEngine {

	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public Base base;
	public WebElement element;
	public WebDriverWait wait;
	public String elementText;
	public final String Scenario_Sheet_Path = "D:\\Eclipse\\AnchalKeywordDriven2\\src\\com\\sit\\anchal\\scenarios\\Scenarios.xlsx";
    public void startExecution(String sheetName) throws IOException
    {
    		File file =  new File(Scenario_Sheet_Path);
    		FileInputStream fis=new FileInputStream(file);
            XSSFWorkbook wb=new XSSFWorkbook(fis);
            XSSFSheet sheet= wb.getSheet(sheetName);
       
            int k = 0;
		for(int i = 0;i<=sheet.getLastRowNum();i++) {
			try {
			String Locatortype =sheet.getRow(i+1).getCell(k+1).toString().trim();
			String Locatorvalue =sheet.getRow(i+1).getCell(k+2).toString().trim();
			String action =sheet.getRow(i+1).getCell(k+3).toString().trim();
			String value =sheet.getRow(i+1).getCell(k+4).toString().trim();
						
			switch (action) {
			case "open browser": 
				base = new Base();
				prop = base.init_properties();
				if(value.isEmpty() || value.equals("NA")){
					driver=base.init_driver(prop.getProperty("browser"));
					driver.manage().window().maximize();
					}else {
					driver = base.init_driver(value);
					driver.manage().window().maximize();
					}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				break;

			case "enter url":
				if(value.isEmpty() || value.equals("NA")){
					driver.get(prop.getProperty("url"));
					}else {
					driver.get(value);
					}
				break;				
				
			case "wait":
				if(value.isEmpty() || value.equals("NA")){
					driver.get(prop.getProperty("url"));
					}else {
					wait = new WebDriverWait(driver, 6);
					}
				break;	
				
			default:
				break;
			}
			switch (Locatortype) {
			case "id":
				element = driver.findElement(By.id(Locatorvalue));
				if(action.equalsIgnoreCase("sendKeys")) {
					element.clear();				
					element.sendKeys(value);
				} else if (action.equalsIgnoreCase("click")) {
					element.click(); 
				} else if(action.equalsIgnoreCase("isDisplayed")){
					element.isDisplayed();
					} else if(action.equalsIgnoreCase("getText")){
						element.getText();
						System.out.println("Text from element is : " + elementText);
					}
				Locatortype=null;
				break;

			case "name":
				element = driver.findElement(By.name(Locatorvalue));
				if(action.equalsIgnoreCase("sendKeys")) {
					element.clear();
					element.sendKeys(value);
				} else if (action.equalsIgnoreCase("click")) {
					element.click(); 
				}  else if(action.equalsIgnoreCase("isDisplayed")){
					element.isDisplayed();
					} else if(action.equalsIgnoreCase("getText")){
						String elementText = element.getText();
						System.out.println("Text from element is : " + elementText);
					}
				Locatortype=null;
				break;	
				
				
			case "xpath":
				element = driver.findElement(By.xpath(Locatorvalue));
				if(action.equalsIgnoreCase("sendKeys")) {
					element.clear();				
					element.sendKeys(value);
				} else if (action.equalsIgnoreCase("click")) {
					element.click(); 
				}  else if(action.equalsIgnoreCase("isDisplayed")){
					element.isDisplayed();
					} else if(action.equalsIgnoreCase("getText")){
						 elementText = element.getText();
						System.out.println("Text from element is : " + elementText);
					} else if(action.equalsIgnoreCase("Assert")){
						Assert.assertEquals(elementText, value);
									
						element.isDisplayed();
						} 
				Locatortype=null;
				break;
				
			default:
				break;
			}
		}
			catch(Exception e) {
				System.out.println(e.getMessage());	
			}
			
			}
    	
    	}

}
