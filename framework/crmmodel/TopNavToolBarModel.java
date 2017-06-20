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
 * @since  December 18, 2013
 */
public  class TopNavToolBarModel extends WebDriverModelBase
{
	private WebDriver driver = null;
	public TopNavToolBarModel(WebDriver driver){
		super(driver);
		this.driver = driver;
	}

	public WebElement container(){
		WebElement container = null;
		try {
			
			driver.switchTo().defaultContent();  
			System.out.print("");
			driver.switchTo().defaultContent(); 
			driver.switchTo().frame("CRMApplicationFrame");
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='APP_FRAME_ANCHOR']/iframe[1]")));
			driver.switchTo().frame("CRMApplicationFrame");
			driver.switchTo().frame("FRAME_APPLICATION");
			driver.switchTo().frame("HeaderFrame");
			container = driver.findElement(By.id("rootAreaDiv"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
				
		return container;
	}
	
	public WebElement endButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C5_W15_V16_End']/span/b"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
}



