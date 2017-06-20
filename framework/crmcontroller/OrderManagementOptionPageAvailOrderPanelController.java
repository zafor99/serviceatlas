package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.OrderManagementOptionPageAvailOrderPanelModel;
import framework.crmmodel.OrderManagementServReqPageServiceReqPanelModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  February 06, 2014
 */
public  class OrderManagementOptionPageAvailOrderPanelController extends WebDriverControllerBase
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementServReqPageServiceReqPanelController.class);
	private OrderManagementOptionPageAvailOrderPanelModel availableOrderChangeOptionsPanel  = null;
	public OrderManagementOptionPageAvailOrderPanelController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		availableOrderChangeOptionsPanel = new OrderManagementOptionPageAvailOrderPanelModel(driver);
	}
	
	public void refund(){
		logger.info("refund()");
		availableOrderChangeOptionsPanel.returnDebitRefundRadioButton().click();
		getExecutingScript().delayFor(2);
		availableOrderChangeOptionsPanel.issueRefundRadioButton().click();
		getExecutingScript().delayFor(2);
		availableOrderChangeOptionsPanel.refundRadioButton().click();
		
	}
}
