package framework.crmmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rational.test.ft.script.RationalTestScript;

import framework.webdrivermodel.WebDriverModelBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  February 06, 2014
 */
public  class OrderManagementOptionsPageModel extends WebDriverModelBase
{
private WebDriver driver = null;
	
	public OrderManagementOptionsPageModel(WebDriver driver){
		super(driver);	
		this.driver = driver;
	}
	public WebElement parentContainer(){
		WebElement container = null;
		try {
			container = driver.findElement(By.xpath("//div[@id='C19_W70_V71_V114']"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
				
		return container;
	}
	
}