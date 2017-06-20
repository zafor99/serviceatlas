package framework.sapcontroller;

import org.apache.log4j.Logger;

import utils.BNTimer;

import com.bn.qa.xobject.XPlaybackMonitor;
import com.bn.qa.xobject.XTimer;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPDeliveryRequestPTNDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 27, 2013
 */
public class SAPDeliveryRequestPTNDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPDeliveryRequestPTNDialogController.class);
	private static SAPDeliveryRequestPTNDialogModel appModel = new SAPDeliveryRequestPTNDialogModel();
	
	public SAPDeliveryRequestPTNDialogController(){
		super(appModel.dialog());		
	}
	
	public Toolbar toolbar(){
		return new Toolbar();
	}
	public PurchaseOrderHistoryTab purchaseOrderHistoryTab(){
		return new PurchaseOrderHistoryTab();
	}
	public ConfirmationsTab confirmationsTab(){
		return new ConfirmationsTab();
	}

	
	public String getBNDropShipPONumber(){
		String dropShipPONumber = null;
		dropShipPONumber = appModel.bnDropShipPONumberTextBox().getText();
		System.out.println("BN dropShipPONumber : " + dropShipPONumber);
		return dropShipPONumber;
	}
	public class Toolbar{
		public Toolbar(){
			
		}
		/*
		 * Click Other Purchase  Order Button
		 */
		public void clickOtherPurchaseOrder(){
			logger.info("clickOtherPurchaseOrder()");
			appModel.toolBar().otherPurchaseOrderButton().click();
		}
		/*
		 * Click Other Document
		 */
		public void clickOtherDocument(){
			logger.info("clickOtherDocument()");
			appModel.toolBar().otherDocumentButton().click();
			delayFor(2);
		}
	}
	public class PurchaseOrderHistoryTab{
		public PurchaseOrderHistoryTab(){
			
		}
		/*
		 * Select the Tab
		 */
		public void select(){
			logger.info("select()");
			
			boolean isActive = (Boolean) appModel.purchaseOrderHistoryTab().tab().getProperty("Changeable");
			if(appModel.purchaseOrderHistoryTab().tab().exists()){
				if(!isActive){
					appModel.purchaseOrderHistoryTab().tab().click();
				}
				
				delayFor(2);
				
			}
			else{
				RationalTestScript.logError("Purchase History Tab is not generated");	
			}
			
			
		}
		
		public boolean exists()
		{		
			boolean result = false;
			result = appModel.purchaseOrderHistoryTab().tab().isOpaque();
			
			//RationalTestScript.logTestResult("No Search Result Page Exists", result);
			
			return result;
		}
		public String getIRNumber(){
			logger.info("getIRNumber()");
			
			String irNumber = "";
			String temp = null;
			
			int count = (Integer)appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getProperty("RowCount");
//			System.out.println(appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getColumnName("Sh. Text"));//   Value(0, "BELNR");
			
			for(int i=0;i<count;i++){
				temp = appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getCellValue(i, "BEWTK");
				if(temp.contains("IR-L")){
					irNumber = appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getCellValue(i, "BELNR");		
				}
			}
			if(irNumber==""){
				RationalTestScript.logError("IR number not generated");
			}
			
			return irNumber;
		}
		/*
		 * @param vpName Name of the verification point 
		 * Verify Purchase History Table
		 */
		public boolean  verifyPurchaseHistoryTable(String vpName){
			logger.info("verifyPurchaseHistoryTable("+vpName+")");
			boolean result = false;
			ITestData testData = null;
			if(appModel.purchaseOrderHistoryTab().tab().exists()){
				testData= appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getTestData("list");
				result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			}else{
				RationalTestScript.logError("Purchase History Tab is not generated");
			}
			
/*			if(appModel.purchaseOrderHistoryTab().tab()!=null){
				testData= appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getTestData("list");
				result = getExecutingScript().vpManual(vpName, testData).performTest();
			}
			else{
				getExecutingScript().sap().appsDownloadPODialog().topLevelToolbar().navigateBack();
				getExecutingScript().sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
				testData= appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getTestData("list");
				result = getExecutingScript().vpManual(vpName, testData).performTest();
			}*/
			return result;
		}
		/*
		 * @param maxTime Max Time(sec) to wait for IR Generation 
		 * Wait for IR(Invoice Receive) Generation
		 */
		public void waitForGRGeneration(int maxTime){
			BNTimer timer = new BNTimer();
			boolean result = false;
			int row;
			timer.start();
			int rows = Integer.valueOf(appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getProperty("RowCount").toString());
			do{			
				String rowName = appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getColumnName("Text");
			//	int rownum = appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getColumnPosition("");
				
				for(int i=0; i<rows;i++){
					String rowContent= appModel.purchaseOrderHistoryTab().purchaseOrderHistoryGrid().getCellValue(i, rowName);
					if(rowContent.contains("IR")){
						result = true;
						break;
					}
					AtlasScriptbase.getExecutingScript().sap().deliveryRequestPTNDialog().topLevelToolbar().navigateBack();
					AtlasScriptbase.getExecutingScript().sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
				}
				if(timer.getElapsedTime()>=maxTime){
					RationalTestScript.logError("Search timeout in 6 mins");
					break;
				}
			}while(result==false);
			
		}
	}
	public class ConfirmationsTab{
		public ConfirmationsTab(){
			
		}
		/*
		 * Select the Tab
		 */
		public void select(){
			logger.info("select()");
			boolean isActive = (Boolean) appModel.confirmationTab().tab().getProperty("Changeable");
			if(!isActive){
				appModel.confirmationTab().tab().click();
			}
	
			delayFor(2);
		}
		/*
		 * @param vpName Name of the verification point 
		 * Verify Confirmation Table
		 */
		public boolean  verifyConfirmationTable(String vpName){
			logger.info("verifyConfirmationTable("+vpName+")");
			boolean result = false;
			ITestData testData;
			boolean isActive = (Boolean) appModel.confirmationTab().tab().getProperty("Changeable");

			if(isActive){
				testData = appModel.confirmationTab().confirmationTable().getTestData("list");	
			}
			else{
				select();
				testData = appModel.confirmationTab().confirmationTable().getTestData("list");	
			}
			result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
			return result;
		}
	}
	
	

}
