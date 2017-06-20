package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPBNOrderDataRefreshDialogModel;
import framework.sapmodel.SAPCustomerInteractionCenterDialogModel;
import framework.sapmodel.SAPBNOrderDataRefreshDialogModel.SchedulesNPOsTab;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 23, 2013
 */
public  class SAPBNOrderDataRefreshDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPBNOrderDataRefreshDialogController.class);
	private static SAPBNOrderDataRefreshDialogModel appModel = new SAPBNOrderDataRefreshDialogModel();
	
	public SAPBNOrderDataRefreshDialogController(){
		super(appModel.dialog());
	}
	public SchedulesNPOsTab schedulesNPOsTab(){
		return new SchedulesNPOsTab();
	}
	public ItemsTab itemsTab(){
		return new ItemsTab();
	}
	public paymentCardsTab paymentCardsTab(){
		return new paymentCardsTab();
	}
	public void selectSchedulesNPOsTab(){
		logger.info("selectSchedulesNPOsTab()");
		appModel.schedulesNPOsTab().tab().select();
		delayFor(3);
	}
	public void selectItemsTab(){
		logger.info("selectItemsTab()");
		appModel.itemsTab().tab().select();
		delayFor(3);
	}
	public void selectPaymentCardsTab(){
		logger.info("selectPaymentCards()");
		appModel.paymentCardsTab().tab().select();
		delayFor(3);
		
	}
	public class paymentCardsTab{
		public paymentCardsTab(){
			
		}
		public void clickBillToAddress(){
			logger.info("clickBillToAddress()");
			appModel.paymentCardsTab().billToAdressButton().click();
			delayFor(3);
		}
		public void clickAddPaymanet(){
			logger.info("clickAddPaymanet()");
			appModel.paymentCardsTab().addPaymentButton().click();
			delayFor(3);
		}
		public boolean verifyPaymentCardsTable(String vpName){
			logger.info("verifyPaymentCardsTable("+vpName+")");
			boolean result = false;
			ITestData testData;
			testData = appModel.paymentCardsTab().paymentCardsTable().getTestData("list");	
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			return result;
		}
	}
	public class SchedulesNPOsTab{
		public SchedulesNPOsTab(){
			
		}
		public boolean  verifyScheduleSummaryTable(String vpName){
			logger.info("verifyScheduleSummaryTable("+vpName+")");
			boolean result = false;
			ITestData testData;
			boolean isActive = (Boolean) appModel.schedulesNPOsTab().scheduleSummaryTable().getProperty("Changeable");

			if(isActive){
				testData = appModel.schedulesNPOsTab().scheduleSummaryTable().getTestData("list");	
			}
			else{
				selectSchedulesNPOsTab();
				testData = appModel.schedulesNPOsTab().scheduleSummaryTable().getTestData("list");	
			}
		
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			return result;
		}
		public boolean  verifyWarehousePOStatusTable(String vpName){
			logger.info("verifyWarehousePOStatusTable("+vpName+")");
			boolean result = false;
			ITestData testData;
			boolean isActive = (Boolean) appModel.schedulesNPOsTab().warehousePOStatusTable().getProperty("Changeable");

			if(isActive){
				testData = appModel.schedulesNPOsTab().warehousePOStatusTable().getTestData("list");	
			}
			else{
				selectSchedulesNPOsTab();
				testData = appModel.schedulesNPOsTab().warehousePOStatusTable().getTestData("list");	
			}
			
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			return result;
		}
	}
	public void clickEmail(){
		logger.info("clickEmail()");
		appModel.toolBar().emailButton().click();
		delayFor(5);
	}
	public class ItemsTab{
		public ItemsTab(){
			
		}
		public void cancel(){
			logger.info("cancel()");
			appModel.itemsTab().cancelButton().click();
			
		}
		public void select1stItem(){
			logger.info("select1stItem()");
			appModel.itemsTab().summaryTable().selectRow(atRow(atIndex(0)));
		}
		public boolean verisySummaryTable(String vpName){
			logger.info("verisySummaryTable("+vpName+")");
			boolean result = false;
			ITestData testData;
			boolean isActive = (Boolean) appModel.itemsTab().summaryTable().getProperty("Changeable");
			if(isActive){
				testData = appModel.itemsTab().summaryTable().getTestData("list");	
			}
			else{
				selectSchedulesNPOsTab();
				testData = appModel.itemsTab().summaryTable().getTestData("list");	
			}
			
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			return result;
		}
	}
	
}
