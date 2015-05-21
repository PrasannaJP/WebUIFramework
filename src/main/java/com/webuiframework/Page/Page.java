package com.webuiframework.Page;

import org.openqa.selenium.WebDriver;

import com.webuiframework.browserdrivers.BrowserDriver;

public class Page {
	private WebDriver _driver;
	
		
	public Page(){
		_driver = BrowserDriver.getDriver();
	}
	
	public void GotoPage(){
		
		_driver.get(BrowserDriver.getApplicationURL());
	}
	
}
