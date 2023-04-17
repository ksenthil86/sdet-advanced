package com.virtusa.sdetselenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
	
	
	@BeforeSuite
	public void setUp() {
		readProperties();
		url=prop.getProperty("url");
		reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/resources/output.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
				
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
	
	@Parameters({"username","password"})
	@DataProvider(name="TestData")
	public HashMap<String,String> readTestData(String username,String password) {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("username", username);
		map.put("password", password);
		return map;
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

}
