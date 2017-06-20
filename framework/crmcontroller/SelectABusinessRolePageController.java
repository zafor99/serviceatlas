package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.SelectABusinessRolePageModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  November 21, 2013
 */
public  class SelectABusinessRolePageController extends WebDriverControllerBase
{
	private static  Logger logger = Logger.getLogger(SelectABusinessRolePageController.class); 
	private SelectABusinessRolePageModel selectABusinessRolePage = null;
	public SelectABusinessRolePageController(WebDriver driver) {
		super(driver);
		selectABusinessRolePage = new SelectABusinessRolePageModel(driver);
	}
	
	public boolean waitForExistence(){
		
		boolean result = false;
		
		result = waitForElementPresent(selectABusinessRolePage, 60);
		
		return result;
	}
	
	public void clickZBNT1CSRLink(){
		logger.info("clickZBNT1CSRLink()");
		driver.switchTo().defaultContent(); 
		selectABusinessRolePage.ZBNT1CSRLink().click();
		waitUntillBrowserRenders();
	}
}
