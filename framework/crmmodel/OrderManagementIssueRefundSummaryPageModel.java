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
 * @since  February 07, 2014
 */
public  class OrderManagementIssueRefundSummaryPageModel extends WebDriverModelBase
{
	private WebDriver driver = null;
	public OrderManagementIssueRefundSummaryPageModel(WebDriver driver){
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
			driver.switchTo().frame("CRMApplicationFrame");
			driver.switchTo().frame("FRAME_APPLICATION");
			driver.switchTo().frame("WorkAreaFrame1");
			container = driver.findElement(By.id("C31_W120_V130"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
		return container;
	}
	public WebElement updateButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C31_W120_V130_thtmlb_button_2']/span/b"));
			
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	
	public WebElement cancelButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C31_W120_V130_thtmlb_button_3']/span/b"));
			
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public CRMCustomTableModel RefundOptionsSummaryTable(){		
		return new CRMCustomTableModel(driver, container(), By.xpath("//table[@id='C31_W120_V130_ConfCellTable_TableHeader']"));
	}
}
