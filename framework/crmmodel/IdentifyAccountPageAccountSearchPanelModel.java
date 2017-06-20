package framework.crmmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;

import com.rational.test.ft.script.RationalTestScript;

import framework.webdrivermodel.WebDriverModelBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  November 21, 2013
 */
public  class IdentifyAccountPageAccountSearchPanelModel extends WebDriverModelBase
{
	private WebDriver driver = null;
	public IdentifyAccountPageAccountSearchPanelModel(WebDriver driver){
		super(driver);
		this.driver = driver;
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
			driver.switchTo().frame("WorkAreaFrame1");
			container = driver.findElement(By.id("C3_W18_V19"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
				
		return container;
	}
	public WebElement orderNoLabel(){
		return container().findElement(By.id("C3_W18_V19_V20_thtmlb_label_1"));	
	}
	public WebElement orderNoTextBox(){
		WebElement textBox = null;		
		try {
			textBox = container().findElement(By.xpath("//input[@id='C3_W18_V19_V20_searchorder_struct.object_id']"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return textBox;
	}
	public WebElement emailAddressLabel(){
		return container().findElement(By.id("C3_W18_V19_V20_thtmlb_label_2"));	
	}
	public WebElement emailAddressTextBox(){
		return container().findElement(By.id("C3_W18_V19_V20_searchcustomer_struct.email"));	
	}
	public WebElement phoneNoLabel(){
		return container().findElement(By.id("C3_W18_V19_V20_thtmlb_label_3"));	
	}
	public WebElement phoneNoTextBox(){
		return container().findElement(By.id("C3_W18_V19_V20_searchcustomer_struct.telephone"));	
	}
	public WebElement firstAndLastNameLabel(){
		return container().findElement(By.id("C3_W18_V19_V20_thtmlb_label_4"));	
	}
	public WebElement firstAndLastNameTextBox(){
		return container().findElement(By.id("C3_W18_V19_V20_searchcustomer_struct.mc_name2"));	
	}
	public WebElement membershipStoreAccountIDLabel(){
		return container().findElement(By.id("C3_W18_V19_V20_thtmlb_label_5"));	
	}
	public WebElement membershipStoreAccountIDTextBox(){
		return container().findElement(By.id("C3_W18_V19_V20_searchcustomer_struct.idnumber"));	
	}
	public WebElement searchAccountButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C3_W18_V19_V20_Search']/span/b"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement clearButton(){
		return container().findElement(By.xpath("//a[@id='C3_W18_V19_V20_Reset']/span/b"));	
	}
	public WebElement createButton(){
		return container().findElement(By.xpath("//a[@id='C3_W18_V19_V20_New']/span/b"));	
	}
	public WebElement advanceSearchLink(){
		return container().findElement(By.id("C3_W18_V19_AdvancedSearch"));	
	}

}
