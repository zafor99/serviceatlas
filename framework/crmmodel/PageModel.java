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
 * @since  November 21, 2013
 */
public class PageModel extends WebDriverModelBase
{
	public PageModel(WebDriver driver){
		super(driver);
	}
	public WebElement container(){
		WebElement container = null;
		try {

			driver.switchTo().defaultContent();  
			System.out.print("");
			driver.switchTo().frame("CRMApplicationFrame");
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='APP_FRAME_ANCHOR']/iframe[1]")));
			//driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='APP_FRAME_ANCHOR']/iframe[@title='Main Client              ']")));
			driver.switchTo().frame("CRMApplicationFrame");
			driver.switchTo().frame("FRAME_APPLICATION");
			driver.switchTo().frame("HeaderFrame");
			container = driver.findElement(By.id("th-l-headercontainer"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}

		return container;
	}
	public Header topPanel(){
		return new Header();
	}
	public LeftNavBar leftNavBar(){
		return new LeftNavBar();
	}
	public class Header{
		public WebElement header = null;
		public Header(){
			//header =  getDriver().findElement(By.id("th-l-headercontainer"));	
		}

		public WebElement acceptButton(){
			return container().findElement(By.id("C5_W15_V16_Accept"));		
		}
		public WebElement logOffButton(){
			return container().findElement(By.id("C5_W15_V16_Logoff"));		
		}

		public WebElement logOnButton(){
			return container().findElement(By.id("C5_W15_V16_Logon"));		
		}

		public WebElement newSessionButton(){
			return container().findElement(By.id("C5_W15_V16_NewSession"));		
		}

		public WebElement conferenceButton(){
			return container().findElement(By.id("C5_W15_V16_Conference"));		
		}
		public WebElement consultButton(){
			return container().findElement(By.id("C5_W15_V16_Consult"));		
		}
		public WebElement holdButton(){
			return container().findElement(By.id("C5_W15_V16_Hold"));		
		}
		public WebElement retrieveButton(){
			return container().findElement(By.id("C5_W15_V16_UnHold"));		
		}
		public WebElement HangUpButton(){
			return container().findElement(By.id("C5_W15_V16_HangUp"));		
		}
		public WebElement toggleButton(){
			return container().findElement(By.id("C5_W15_V16_Alternate"));		
		}
		public WebElement dialPadButton(){
			return container().findElement(By.id("C5_W15_V16_DialPad"));		
		}
		public WebElement endButton(){
			return container().findElement(By.id("C5_W15_V16_End"));		
		}
	}

	public class LeftNavBar{

	}
}
