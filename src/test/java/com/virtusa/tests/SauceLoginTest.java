package com.virtusa.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.virtusa.sdetselenium.BaseClass;
import com.virtusa.sdetselenium.SauceLoginPage;
import com.virtusa.sdetselenium.TestUtils;

public class SauceLoginTest extends BaseClass{
	
	
	@BeforeTest
	public void setup() {
		driver.navigate().to(url);		
	}
	
	@Parameters({"username","password"})
	@Test
	public void loginTest(String username,String password){
		SauceLoginPage sauceLoginPage = new SauceLoginPage(driver);
		String expectedTitle = TestUtils.readTestData("testdata").get("title");
		sauceLoginPage.verifyTitle(expectedTitle);
		System.out.println("Verified Page Title - "+expectedTitle);
		sauceLoginPage.login(username,password);
		System.out.println("Verify Login Test");
		TestUtils.takeScreenshot(driver,"login test");
	}
	
	

}
