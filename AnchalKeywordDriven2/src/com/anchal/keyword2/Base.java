package com.anchal.keyword2;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {

	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browsername){
		if(browsername.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver.exe"); 
			if(prop.getProperty("headless").equals("yes")) {
				//headless mode:
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--headless");
				driver = new ChromeDriver(option);
				}
			driver = new ChromeDriver();
			}
		return driver;
	}
	      public Properties init_properties()
	      {
	    	  prop = new Properties();
	    	  try {
				FileInputStream ip = new FileInputStream("D:\\Eclipse\\AnchalKeywordDriven\\src\\com\\sit\\anchal\\com\\config.properties");
			prop.load(ip);
			} 
	    	  catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 	  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    return prop;
	      } 
	    } 


