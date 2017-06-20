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
public  class OrderManagementSummaryPageModel extends WebDriverModelBase
{
	private WebDriver driver = null;
	public OrderManagementSummaryPageModel(WebDriver driver){
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
			container = driver.findElement(By.id("C19_W70_V71_V131"));
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
		return container;
	}
	public CRMCustomTableModel summaryTable(){		
		return new CRMCustomTableModel(driver, container(), By.xpath("//table[@id='C19_W70_V71_V131_V132_ConfCellTable_TableHeader']"));
	}
}
