package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPCICDisplayChangeOrderDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public class SAPCICDisplayChangeOrderDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPCICDisplayChangeOrderDialogController.class);
	private static SAPCICDisplayChangeOrderDialogModel appModel = new SAPCICDisplayChangeOrderDialogModel();
	
	public SAPCICDisplayChangeOrderDialogController(){
		super(appModel.dialog());
	}
	
	public boolean waitForExistence(){
		logger.info("waitForExistence");
		boolean result = false;

		appModel.dialog().waitForExistence(120, 5);
		if(appModel.dialog().exists()){
			result = true;
		}
		return result;
	}
	
	public void close(){
		logger.info("close");
		appModel.dialog().close();
		AtlasScriptbase.getExecutingScript().sap().logOffDialog().clickYesButton();
		AtlasScriptbase.getExecutingScript().delayFor(3);
		
	}
	
	public void selectOrdersTab(){
		logger.info("selectOrdersTab");
		appModel.ordersTab().tab().select();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}
	
	public void selectItemsTab(){
		logger.info("selectItemsTab");
		appModel.itemsTab().tab().select();
		AtlasScriptbase.getExecutingScript().delayFor(3);
	}
	
	public void selectInvoiceTab(){
		logger.info("selectInvoiceTab");
		appModel.invoiceTab().tab().select();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}
	
	public boolean verifyOrdersTable(String vpName, String orderNum){
		logger.info("verifyOrdersTable("+vpName+","+orderNum+")");
		boolean result = false;
		String[][] data = appModel.ordersTab().orderHistoryTable().getRowTextData("Order#", orderNum);
		ITestData testData = VpUtil.getTestData(data);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		
		return result;
	}
	
	public boolean verifyItemSummaryTable(String vpName){
		logger.info("verifyItemSummaryTable("+vpName+")");
		boolean result = false;
		String[][] data = appModel.itemsTab().itemSummaryTable().getTableTextData();
		ITestData testData = VpUtil.getTestData(data);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		
		return result;
	}
	
	public boolean verifyInvoiceDetailsTable(String vpName){
		logger.info("verifyInvoiceDetailsTable("+vpName+")");
		boolean result = false;
		String[][] data = appModel.invoiceTab().invoiceDetailsTable().getTableTextData();
		ITestData testData = VpUtil.getTestData(data);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		
		return result;
	}
}
