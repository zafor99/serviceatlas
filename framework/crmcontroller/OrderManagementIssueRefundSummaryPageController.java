package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.OrderManagementIssueRefundSummaryPageModel;



/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  February 07, 2014
 */
public  class OrderManagementIssueRefundSummaryPageController extends PageController
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementIssueRefundPageIssueRefundPanelController.class);
	private OrderManagementIssueRefundSummaryPageModel appModel  = null;
	public OrderManagementIssueRefundSummaryPageController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		appModel = new OrderManagementIssueRefundSummaryPageModel(driver);
	}
	public void selectPaymentType(String paymentType){
		logger.info("selectPaymentType("+paymentType+")");
		WebElement cell = null;
		
		int rowIndex = -1;
		rowIndex = appModel.RefundOptionsSummaryTable().getRowIndexByCellText("Card Type", paymentType);
		
		try {
				cell = appModel.RefundOptionsSummaryTable().getRowSelectionCell(rowIndex);
				if(cell!=null){
					cell.click();
				//	waitUntillBrowserRenders();
					//cell.click();
				}
				
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
	}
	public void update(){
		logger.info("update()");
		appModel.updateButton().click();
		waitUntilProcessingEnds();
		
	}
	
}
