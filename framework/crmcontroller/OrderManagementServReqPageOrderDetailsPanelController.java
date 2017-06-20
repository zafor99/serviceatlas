package framework.crmcontroller;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rational.test.ft.script.RationalTestScript;

import framework.crmmodel.IdentifyAccountPageOrdersTabPanelModel;
import framework.crmmodel.OrderManagementServReqPageOrderDetailsPanelModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  January 28, 2014
 */
public  class OrderManagementServReqPageOrderDetailsPanelController extends WebDriverControllerBase
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(OrderManagementServReqPageOrderDetailsPanelController.class); 
	private OrderManagementServReqPageOrderDetailsPanelModel appModel  = null;
	public OrderManagementServReqPageOrderDetailsPanelController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		appModel = new OrderManagementServReqPageOrderDetailsPanelModel(driver);
	}
	public void selectItemByItemNum(String itemNum){
		logger.info("selectOrderTableRowbyOrderNum("+itemNum+")");
		WebElement cell = null;
		WebElement element = null;
		
		int rowIndex = -1;
		rowIndex = appModel.orderItemsDetailTable().getRowIndexByCellText("Item #", itemNum);
		
		try {
				cell = appModel.orderItemsDetailTable().getRowSelectionCell(rowIndex);
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
	
}
