package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.OrderManagementOrderPageModel;
import framework.crmmodel.OrderManagementServReqPageModel;
import framework.crmmodel.OrderManagementServReqPageServiceReqPanelModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  January 28, 2014
 */
public  class OrderManagementServReqPageController extends PageController
{
	private WebDriver driver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementServReqPageController.class);
	private OrderManagementServReqPageModel servReqPageModel  = null;
	private OrderManagementTopHeaderPanelController topHeaderPanel = null;
	private OrderManagementServReqPageServiceReqPanelController serviceRequestPanel = null;
	private OrderManagementServReqPageOrderDetailsPanelController orderDetailsPanel = null;
	private OrderManagementServReqPageServiceRequestOverviewPanelController serviceRequestOverviewPanel = null;
	public OrderManagementServReqPageController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		servReqPageModel = new OrderManagementServReqPageModel(driver);
	}
	public OrderManagementTopHeaderPanelController topHeaderPanel(){
		if(topHeaderPanel ==null){
			topHeaderPanel = new OrderManagementTopHeaderPanelController(driver);
		}
		return topHeaderPanel;
	}
	public OrderManagementServReqPageServiceReqPanelController createServiceRequestPanel(){
		if(serviceRequestPanel ==null){
			serviceRequestPanel = new OrderManagementServReqPageServiceReqPanelController(driver);
		}
		return serviceRequestPanel;
	}
	public OrderManagementServReqPageOrderDetailsPanelController orderDetailsPanel(){
		if(orderDetailsPanel ==null){
			orderDetailsPanel = new OrderManagementServReqPageOrderDetailsPanelController(driver);
		}
		return orderDetailsPanel;
	}
	public OrderManagementServReqPageServiceRequestOverviewPanelController serviceRequestOverviewPanel(){
		if(serviceRequestOverviewPanel ==null){
			serviceRequestOverviewPanel = new OrderManagementServReqPageServiceRequestOverviewPanelController(driver);
		}
		return serviceRequestOverviewPanel;
	}
}
