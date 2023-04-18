package com.virtusa.sdetselenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtils {
	
	public static void takeScreenshot(WebDriver driver,String name) {
		try {
			
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String filename = name+"_"+System.currentTimeMillis()+".png";
			String destPath = System.getProperty("user.dir")+"/output/"+filename;
			File destFile = new File(destPath);
			FileUtils.copyFile(srcFile, destFile);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String,String> readTestData(String fileName) {
		
		FileInputStream is = null;
		String testDataFileName = System.getProperty("user.dir")+"/src/test/java/resources/"+fileName+".json";
		try {
		 is = new FileInputStream(testDataFileName);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONObject object = new JSONObject(new JSONTokener(is));
		JSONObject testdataobject = new JSONObject(object.getJSONArray("data").get(0).toString());
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("title",testdataobject.get("title").toString());
		return map;
		
	}

}
