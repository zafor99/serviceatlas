package framework.crmcontroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;
import com.rational.test.ft.vp.impl.VPManager;

import framework.AtlasScriptbase;
import framework.crmmodel.IdentifyAccountPageOrdersTabPanelModel;
import framework.webdrivercontroller.WebDriverControllerBase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  December 03, 2013
 */
public  class IdentifyAccountPageOrdersTabPanelController extends WebDriverControllerBase
{
	private WebDriver webdriver = null;
	private static  Logger logger = Logger.getLogger(IdentifyAccountPageOrdersTabPanelController.class); 
	private IdentifyAccountPageOrdersTabPanelModel appModel  = null;
	public IdentifyAccountPageOrdersTabPanelController(WebDriver driver) {
		super(driver);
		this.driver = driver;
		appModel = new IdentifyAccountPageOrdersTabPanelModel(driver);
	}
	public boolean verifyOrdersTable(String vpName){
		logger.info("verifyOrdersTable("+vpName+")");
		boolean result = false;
		String[][] data = null;
		data = 	appModel.ordersTable().getTableCellData();
		
		ITestData testData = VpUtil.getTestData(data);
		
		result = getExecutingScript().vpManual(vpName, testData).performTest();
		AtlasScriptbase.writeResultToExternalSources(vpName, null, result);
		return result;
	}
	public String getOrderStatus(String orderNumber){
		String orderStatus = null;
		int rowIndex = 0;
		rowIndex = appModel.ordersTable().getRowIndexByCellText("Order No", orderNumber);
		orderStatus = appModel.ordersTable().getCellText(rowIndex, "Order Status");
		return orderStatus;
	}
	public void openSAPOrderNo(String orderNumber){
		logger.info("openSAPOrderNo("+orderNumber+")");
		WebElement cell = null;
		WebElement element = null;
		
		int rowIndex = 0;
		rowIndex = appModel.ordersTable().getRowIndexByCellText("Order No", orderNumber);
		
		try {
			cell = appModel.ordersTable().getCell(rowIndex, "SAP Order No");
			if(cell!=null){
				element = cell.findElement(By.xpath("a"));
				if(element!=null){
					element.click();
					waitUntilProcessingEnds();
				}
			}
			else
			{
				RationalTestScript.logError(orderNumber + " Order not found in Table");
			}
		} catch (NoSuchElementException ex) {
			//ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
		
		
	}
	
	public String getSAPOrderNumberByOrderStatus(String orderNumber, String orderStatus){
		logger.info("getSAPOrderNumberByOrderStatus("+orderNumber+"),("+orderStatus+")");
		String sapOrderNumber = "";
		int rowIndex = 0;
		WebElement element = null;
		
		rowIndex = appModel.ordersTable().getRowIndexByCellText("Order Status", orderStatus);
//		rowIndex = appModel.ordersTable().getRowIndexByCellText("SAP Order No", "6100861158");
		try {
			sapOrderNumber = appModel.ordersTable().getCellText(rowIndex, "SAP Order No");
			
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}catch (NullPointerException ex){
		
		}
		
		return sapOrderNumber;
	}
	public void selectOrderTableRowbyOrderNum(String orderNum){
		logger.info("selectOrderTableRowbyOrderNum("+orderNum+")");
		WebElement cell = null;
		WebElement element = null;
		
		int rowIndex = -1;
		rowIndex = appModel.ordersTable().getRowIndexByCellText("Order No", orderNum);
		
		try {
				cell = appModel.ordersTable().getRowSelectionCell(rowIndex);
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
