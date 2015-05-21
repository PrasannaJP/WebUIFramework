package com.webuiframework.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.webuiframework.browserdrivers.BrowserDriver;

public class PageActions {

	private WebDriver _driver;
	private WebDriverWait waitinterval;
	public PageActions()
	{
		_driver = BrowserDriver.getDriver();
		PageFactory.initElements(BrowserDriver._driver,_driver);
				
		waitinterval = (new WebDriverWait(_driver,10));
		
		
	}
	
	public WebElement WaitForElementToSelectByXpath(String xPath)
	{		
		return (waitinterval.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath))));
	}
	
	public WebElement WaitForElementToSelectByID(String id)
	{		
		return (waitinterval.until(ExpectedConditions.presenceOfElementLocated(By.id(id))));
	}
	
	public WebElement WaitForElementToSelectByCssSelector(String cssSelector)
	{		
		return (waitinterval.until(ExpectedConditions.presenceOfElementLocated(By.id(cssSelector))));
	}
	
	public String getText(WebElement webElelemnt ) {
		String webTextDisplayed = "";
		if(webElelemnt.isDisplayed())
		{
			if(webElelemnt.getTagName().toLowerCase().contains("div"))
			{
				webTextDisplayed = webElelemnt.getText().trim();
			}
			else if (webElelemnt.getTagName().toLowerCase().contains("li")) {
				webTextDisplayed = webElelemnt.getText().trim();
			}
			else if (webElelemnt.getTagName().toLowerCase().contains("select")) {
				Select select = new Select(webElelemnt);
				webTextDisplayed = select.getFirstSelectedOption().getText();
			}
			else
			{
				webTextDisplayed = webElelemnt.getText();
			}
		}
		return webTextDisplayed;
	}
	
	public Boolean IsElementvisible(WebElement webElement) {
		
		return webElement.isEnabled();
	}
	
	public void ElementSetValue(WebElement webElement,String setValue){
		webElement.sendKeys(setValue);
	}
	
	public void ElementClick(WebElement webElement){
		waitinterval.until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
	}
	
	public void SwitchToFrame(WebElement webElement) {
		_driver.switchTo().frame(webElement);
		
	}
	
	public void SwitchToDefaultContent(WebElement webElement) {
		_driver.switchTo().defaultContent();
		
	} 
	
	public void WaitForPageLoad()
	{
		waitinterval.until( new Predicate<WebDriver>() {
            public boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        }
    );
	
	}
	
	
}

	
