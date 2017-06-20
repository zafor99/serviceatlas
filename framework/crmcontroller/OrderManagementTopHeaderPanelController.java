package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.IdentifyAccountPageAccountSearchPanelModel;
import framework.crmmodel.OrderManagementTopHeaderPanelModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  January 28, 2014
 */
public  class OrderManagementTopHeaderPanelController extends WebDriverControllerBase
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementTopHeaderPanelController.class);
	private OrderManagementTopHeaderPanelModel topHeaderPanelModel  = null;
	public OrderManagementTopHeaderPanelController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		topHeaderPanelModel = new OrderManagementTopHeaderPanelModel(driver);
	}
	public void next(){
		logger.info("next()");
		topHeaderPanelModel.nextButton().click();
		waitUntilProcessingEnds();
	}
	public void clickNotificationLink(){
		logger.info("clickNotificationLink()");
		topHeaderPanelModel.notificationLink().click();
		getExecutingScript().delayFor(2);
	}
	public boolean verifyNotification(String vpName){
		logger.info("verifyNotification("+vpName+")");
		boolean result = false;
		String  notificationText = null;
		if(topHeaderPanelModel.notificationText().getText()!=null){
			notificationText = topHeaderPanelModel.notificationText().getText();
		}
		else{
			notificationText = "NO Notification Found";
			
		}
		result = getExecutingScript().vpManual(vpName, notificationText).performTest();
		return result;
	}
	public String getRefundID(){
		logger.info("clickNotificationLink()");
		String refundID= null;
		refundID = topHeaderPanelModel.notificationText().getText();
		refundID = refundID.substring(7, 17);
		return refundID;
	}
}
