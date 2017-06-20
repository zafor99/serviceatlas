package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

import framework.crmmodel.OrderManagementSummaryPageModel;


/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  February 07, 2014
 */
public  class OrderManagementSummaryPageController extends PageController
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementSummaryPageController.class);
	private OrderManagementSummaryPageModel appModel  = null;
	private OrderManagementTopHeaderPanelController topHeaderPanel = null;
	public OrderManagementSummaryPageController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		appModel = new OrderManagementSummaryPageModel(driver);
	}
	public OrderManagementTopHeaderPanelController topHeaderPanel(){
		if(topHeaderPanel ==null){
			topHeaderPanel = new OrderManagementTopHeaderPanelController(driver);
		}
		return topHeaderPanel;
	}
	public boolean verifySummaryTable(String vpName){
		logger.info("verifySummaryTable("+vpName+")");
		boolean result = false;
		String[][] data = null;
		data = 	appModel.summaryTable().getTableCellData();
		
		ITestData testData = VpUtil.getTestData(data);
		
		result = getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
}
