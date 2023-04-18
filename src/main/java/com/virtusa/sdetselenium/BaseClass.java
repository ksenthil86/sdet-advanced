package com.virtusa.sdetselenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public class BaseClass {
	
	public static Properties prop;
	public static String url;
	public static WebDriver driver;
	
	
	@BeforeSuite
	public void setUp() {
		readProperties();
		url=prop.getProperty("url");
		initialize();
				
	}
	
	public void readProperties() {
		
		try {
		prop = new Properties();
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"/src/test/java/resources/"+"application.properties");
		prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	
	public void initialize() {
		
		String browser = prop.getProperty("browser");
		
		if(browser.equals("Edge")) {
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"/src/test/java/resources/msedgedriver.exe");
			driver=new EdgeDriver();
		}else if(browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/test/java/resources/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void teardown() {
		driver.quit();
	}

}
