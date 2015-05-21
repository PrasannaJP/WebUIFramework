package com.webuiframework.browserdrivers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;



public class BrowserDriver {

	public static WebDriver _driver;
	private static Properties properties;
	
	public static WebDriver getDriver() {
		Browsers browser;
				if(getBrowserName() == null){
			browser = Browsers.FIREFOX;
		}else{
			browser = Browsers.browserForName(getBrowserName());
		}
		switch(browser){
			case CHROME:
				_driver = BrowserFactory.createChromeDriver();
				_driver.navigate().to(getApplicationURL());
				break;
			case SAFARI:
				_driver = BrowserFactory.createSafariDriver();
				_driver.navigate().to(getApplicationURL());
				break;
			case FIREFOX:
			default:
				_driver = BrowserFactory.createFirefoxDriver(BrowserFactory.getFirefoxProfile());
				_driver.navigate().to(getApplicationURL());
				break;
		}
		BrowserFactory.addAllBrowserSetup(_driver);
		return _driver;
	}
	
	public static String getBrowserName(){
		loadProperty();
		return properties.getProperty("webUITests.Browser");
		//return "Firefox";
	}
	
	public static String getApplicationURL(){
		loadProperty();
		return properties.getProperty("webUITests.ApplicationURL");
		//return "https://www.google.co.in/";
	}
	
	
	public static void loadProperty(){
		File configFile = new File("config.properties");
		try {
		    FileReader reader = new FileReader(configFile);
		    properties = new Properties();
		    properties.load(reader);
		}
		catch(FileNotFoundException ex){
			
		}
		catch (IOException ex) {
		    // I/O error
		}
		
	}
	
}
	

