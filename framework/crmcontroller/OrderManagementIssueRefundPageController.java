package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.OrderManagementIssueRefundPageModel;
import framework.crmmodel.OrderManagementOptionsPageModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  February 06, 2014
 */
public  class OrderManagementIssueRefundPageController extends PageController
{
	private WebDriver driver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementIssueRefundPageController.class);
	private OrderManagementIssueRefundPageModel issueRefundPageModel  = null;
	private OrderManagementTopHeaderPanelController topHeaderPanel = null;
	private OrderManagementIssueRefundPageIssueRefundPanelController issueRefundPanel = null;
	public OrderManagementIssueRefundPageController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		issueRefundPageModel = new OrderManagementIssueRefundPageModel(driver);
	}
	public OrderManagementTopHeaderPanelController topHeaderPanel(){
		if(topHeaderPanel ==null){
			topHeaderPanel = new OrderManagementTopHeaderPanelController(driver);
		}
		return topHeaderPanel;
	}
	public OrderManagementIssueRefundPageIssueRefundPanelController issueRefundPanel(){
		if(issueRefundPanel ==null){
			issueRefundPanel = new OrderManagementIssueRefundPageIssueRefundPanelController(driver);
		}
		return issueRefundPanel;
	}
}
