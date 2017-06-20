package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.OrderManagementServReqPageModel;
import framework.crmmodel.SelectSalesServiceTransactionPageModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  January 28, 2014
 */
public  class SelectSalesServiceTransactionPageController extends PageController
{
	private WebDriver driver = null;
	private static  Logger logger = Logger.getLogger(SelectSalesServiceTransactionPageController.class);
	private SelectSalesServiceTransactionPageModel selectSalesServiceTransactionPage  = null;
	public SelectSalesServiceTransactionPageController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		selectSalesServiceTransactionPage = new SelectSalesServiceTransactionPageModel(driver);
	}
	
	public void selectFirstServiceRequest(){
		logger.info("selectFirstCRMServiceRequest()");
		getExecutingScript().delayFor(5);
		selectSalesServiceTransactionPage.firstCRMServiceRequestCheckbox().click();
		selectSalesServiceTransactionPage.resetHandle();
		waitUntilProcessingEnds();
		
		
	}
	
}
