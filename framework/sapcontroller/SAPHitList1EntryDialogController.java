package framework.sapcontroller;
import org.apache.log4j.Logger;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPHitList1EntryDialogModel;
import framework.sapmodel.SAPModelBase;
import utils.SpreadSheetUtil;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 14, 2013
 */
public class SAPHitList1EntryDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPHitList1EntryDialogController.class);
	private static SAPHitList1EntryDialogModel appModel = new SAPHitList1EntryDialogModel();
	
	public SAPHitList1EntryDialogController(){
		super(appModel.dialog());
		//super(atDescendant("Id","/wnd[1]","Text"," Hit List 1 Entry"));
		//appModel = new SAPHitList1EntryDialogModel();
	}
	
	public void clickOkButton(){
		logger.info("clickOkButton");
		appModel.okButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}
	
	public void clickCancelButton(){
		logger.info("clickCancelButton");
		appModel.cancelButton().click();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}
	
	public void selectPurchaseOrder(String orderNum){
		logger.info("selectPurchaseOrder("+orderNum+")");
		appModel.getRowGuiTextTestObject("Purchase order no.").doubleClick();
		delayFor(2);
	}
	
	public void selectPurchaseOrder(String orderNum,String columnHeader){
		logger.info("selectPurchaseOrder("+orderNum+")");
		appModel.getRowGuiTextTestObject(columnHeader).doubleClick();
		delayFor(2);
	}
	public void selectOrder(){
		logger.info("selectOrder");
		appModel.webOrderNumberLabel().doubleClick();
		AtlasScriptbase.getExecutingScript().delayFor(2);
	}
	
	public boolean verifyEntryTable(String vpName){
		logger.info("verifyEntryTable("+vpName+")");
		boolean result = false;
		
		String[][] data = new String[2][7];
		
		data[0][0] = appModel.orderStatusHdrLabel().getText();
		data[0][1] = appModel.webOrderNumberHdrLabel().getText();
		data[0][2] = appModel.orderDtHdrLabel().getText();
		data[0][3] = appModel.typeHdrLabel().getText();
		data[0][4] = appModel.soldToEmailAddressHdrLabel().getText();
		data[0][5] = appModel.clientHdrLabel().getText();
		data[0][6] = appModel.feCustomerIdHdrLabel().getText();
		
		
		data[1][0] = appModel.orderStatusLabel().getText();
		data[1][1] = appModel.webOrderNumberLabel().getText();
		data[1][2] = appModel.orderDtLabel().getText();
		data[1][3] = appModel.typeLabel().getText();
		data[1][4] = appModel.soldToEmailAddressLabel().getText();
		data[1][5] = appModel.clientLabel().getText();
		data[1][6] = appModel.feCustomerIdLabel().getText();

		
		
		ITestData testdata = VpUtil.getTestData(data);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testdata).performTest();
		return true;
	}
	
	public boolean verifyEntryTableOrderStatus(String vpName, SpreadSheetUtil spreedSheet, String orderNum, String ordStatus){
		logger.info("verifyEntryTableOrderStatus("+vpName+"),("+spreedSheet+"),("+orderNum+"),("+ordStatus+")");
		boolean result = false;
		
		String[][] dataExp = new String[1][2];
		String[][] dataAct = new String[1][2];
		
		dataExp[0][0] = orderNum;
		dataExp[0][1] = ordStatus;		
		
		dataAct[1][0] = appModel.webOrderNumberLabel().getText();
		dataAct[1][1] = appModel.orderStatusLabel().getText();
				
		spreedSheet.setCellStringValue("Actual", dataAct[1][1]);
		
		ITestData testdataExp = VpUtil.getTestData(dataExp);
		ITestData testdataAct = VpUtil.getTestData(dataAct);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testdataExp,testdataAct).performTest();
		return result;
	}
	
	
}
