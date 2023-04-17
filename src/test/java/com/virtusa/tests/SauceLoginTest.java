package com.virtusa.tests;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.virtusa.sdetselenium.BaseClass;
import com.virtusa.sdetselenium.SauceLoginPage;

public class SauceLoginTest extends BaseClass{
	
	ExtentTest test;
	
	@BeforeTest
	public void setup() {
		driver.navigate().to(url);
		test = extent.createTest("LoginTest");
		
	}
	
	@Parameters({"username","password"})
	@Test
	public void loginTest(String username,String password) {
		SauceLoginPage sauceLoginPage = new SauceLoginPage(driver);
		sauceLoginPage.verifyTitle("Swag Labs");
		test.pass("Verify Page Title");
		sauceLoginPage.login(username,password);
		test.pass("Verify Login Test");
	}
	
	

}
