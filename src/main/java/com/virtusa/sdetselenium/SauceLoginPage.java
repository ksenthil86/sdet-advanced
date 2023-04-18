package com.virtusa.sdetselenium;

import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SauceLoginPage {
	
	final WebDriver driver;
	
	@FindBy(id="user-name")
	WebElement usernameText;
	
	@FindBy(id="password")
	WebElement passwordText;
	
	@FindBy(id="login-button")
	WebElement loginButton;
	
	public SauceLoginPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	public void verifyTitle(String expTitle) {
		Assert.assertEquals(driver.getTitle(),expTitle);
	}
	
	public void login(String userName,String password) {
		usernameText.sendKeys(userName);
		passwordText.sendKeys(password);
		loginButton.submit();
		
	}

}
