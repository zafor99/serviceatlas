package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.rational.test.ft.script.RationalTestScript;
import framework.crmmodel.OrderManagementOptionsPageModel;



/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  February 06, 2014
 */
public  class OrderManagementOptionsPageController extends PageController
{
	private WebDriver driver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementOptionsPageController.class);
	private OrderManagementOptionsPageModel optionsPageModel  = null;
	private OrderManagementTopHeaderPanelController topHeaderPanel = null;
	private OrderManagementOptionPageAvailOrderPanelController availableOrderChangePanel = null;
	public OrderManagementOptionsPageController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		optionsPageModel = new OrderManagementOptionsPageModel(driver);
	}
	public OrderManagementTopHeaderPanelController topHeaderPanel(){
		if(topHeaderPanel ==null){
			topHeaderPanel = new OrderManagementTopHeaderPanelController(driver);
		}
		return topHeaderPanel;
	}
	public OrderManagementOptionPageAvailOrderPanelController availableOrderChangePanel(){
		if(availableOrderChangePanel ==null){
			availableOrderChangePanel = new OrderManagementOptionPageAvailOrderPanelController(driver);
		}
		return availableOrderChangePanel;
	}
}
