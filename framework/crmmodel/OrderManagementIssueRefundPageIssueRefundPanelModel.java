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
public  class OrderManagementIssueRefundPageIssueRefundPanelModel extends WebDriverModelBase
{
	private WebDriver driver = null;
	public OrderManagementIssueRefundPageIssueRefundPanelModel(WebDriver driver){
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
			container = driver.findElement(By.id("C1_W1_V2_C1_W1_V2_V3_C19_W70_V71_C19_W70_V71_V118_C31_W120_V121_IssueRefundOV.do_0003"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
		return container;
	}
	public WebElement reasonCodeComboList(){
		WebElement container = null;
		try {
			
			driver.switchTo().defaultContent();  
			System.out.print("");
			driver.switchTo().frame("CRMApplicationFrame");
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='APP_FRAME_ANCHOR']/iframe[1]")));
			driver.switchTo().frame("CRMApplicationFrame");
			driver.switchTo().frame("FRAME_APPLICATION");
			driver.switchTo().frame("WorkAreaFrame1");
			container = driver.findElement(By.id("C31_W120_V121_V126_V127_invamount_reasoncode__items"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
				
		return container;
	}
	public WebElement reasonCodeInputBox(){
		WebElement button = null;		
		try {
			button = container().findElement(By.id("C31_W120_V121_V126_V127_invamount_reasoncode"));
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	public WebElement updateButton(){
		WebElement button = null;		
		try {
			button = container().findElement(By.xpath("//a[@id='C31_W120_V121_V126_V127_thtmlb_button_1']/span/b"));
			
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return button;
	}
	
	public WebElement issueRefundTableRefundQtyCell(){		
		WebElement table = null;		
		try {
			table = container().findElement( By.xpath("//input[@id='C31_W120_V121_V126_V128_refund_table[1].refund_quantity']"));
//			table = container().findElement( By.id("C31_W119_V120_V125_V127_refund_table[1].refund_quantity"));
			
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
			
		}
		return table;
	}
/*	public CRMCustomTableModel issueRefundTable(){		
		return new CRMCustomTableModel(driver, container(), By.xpath("//table[@id='C31_W120_V121_V126_V128_ConfCellTable_TableHeader']"));

	}*/
	
}
