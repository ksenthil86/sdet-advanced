package com.virtusa.sdetselenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class BaseClass {
	
	public static Properties prop;
	public static String url;
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static WebDriver driver;
	
	
	@BeforeSuite
	public void setUp() {
		readProperties();
		url=prop.getProperty("url");
		reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/resources/output.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
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
	
	
	public void takeScreenshot(WebDriver driver,String name) {
		try {
			
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String filename = name+"_"+System.currentTimeMillis()+".png";
			String destPath = System.getProperty("user.dir")+"/resources/"+filename;
			File destFile = new File(destPath);
			FileUtils.copyFile(srcFile, destFile);
			
		}catch(IOException e) {
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

}
