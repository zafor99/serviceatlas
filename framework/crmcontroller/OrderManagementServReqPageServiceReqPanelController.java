package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.OrderManagementServReqPageServiceReqPanelModel;
import framework.crmmodel.OrderManagementTopHeaderPanelModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  January 28, 2014
 */
public  class OrderManagementServReqPageServiceReqPanelController extends WebDriverControllerBase
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementServReqPageServiceReqPanelController.class);
	private OrderManagementServReqPageServiceReqPanelModel serviceReqPanel  = null;
	public OrderManagementServReqPageServiceReqPanelController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		serviceReqPanel = new OrderManagementServReqPageServiceReqPanelModel(driver);
	}
	public void clickNew(){
		logger.info("next()");
		serviceReqPanel.newButton().click();
		getExecutingScript().delayFor(3);
		waitUntilProcessingEnds();
		getExecutingScript().delayFor(3);
	}
	public void clickKnowledgeBase(){
		logger.info("clickKnowledgeBase()");
		serviceReqPanel.knowledgeBaseButton().click();
		waitUntilProcessingEnds();
	}
	public void save(){
		logger.info("save()");
		serviceReqPanel.saveButton().click();
		waitUntilProcessingEnds();
	}
	
}
