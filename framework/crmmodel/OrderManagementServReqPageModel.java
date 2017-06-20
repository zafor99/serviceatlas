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
 * @since  January 28, 2014
 */
public  class OrderManagementServReqPageModel extends WebDriverModelBase
{
private WebDriver driver = null;
	
	public OrderManagementServReqPageModel(WebDriver driver){
		super(driver);	
		this.driver = driver;
	}
	public WebElement parentContainer(){
		WebElement container = null;
		try {
			container = driver.findElement(By.xpath("//div[@id='C1_W1_V2_V3']"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
				
		return container;
	}
	
}
