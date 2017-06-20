package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.IdentifyAccountPageModel;
import framework.crmmodel.OrderManagementOrderPageModel;
import framework.crmmodel.OrderManagementTopHeaderPanelModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  January 28, 2014
 */
public  class OrderManagementOrderPageController extends PageController
{
	private WebDriver driver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementOrderPageController.class);
	private OrderManagementOrderPageModel orderPageModel  = null;
	private OrderManagementTopHeaderPanelController topHeaderPanel = null;
	
	public OrderManagementOrderPageController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		orderPageModel = new OrderManagementOrderPageModel(driver);
	}
	
	public OrderManagementTopHeaderPanelController topHeaderPanel(){
		if(topHeaderPanel ==null){
			topHeaderPanel = new OrderManagementTopHeaderPanelController(driver);
		}
		return topHeaderPanel;
	}
}
