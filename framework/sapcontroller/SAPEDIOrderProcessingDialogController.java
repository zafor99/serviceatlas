package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPEDIOrderProcessingDialogModel;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 12, 2013
 */
public  class SAPEDIOrderProcessingDialogController extends SAPDialogController
{
	private static Logger logger = Logger.getLogger(SAPEDIOrderProcessingDialogController.class);
	private static 	SAPEDIOrderProcessingDialogModel appModel = new SAPEDIOrderProcessingDialogModel();
	
	public SAPEDIOrderProcessingDialogController() {
		super(appModel.dialog());		
	}
	
	public void searchOrder(String orderDate){
		logger.info("searchOrder("+orderDate+")");
		appModel.orderDateTextBox().click();
		appModel.orderDateTextBox().setText(orderDate);
		appModel.searchOrderButton().click();
		delayFor(5);
	}
	public void searchOrder(String orderDate,String customerOrder){
		logger.info("searchOrder("+orderDate+")");
		appModel.orderDateTextBox().click();
		appModel.orderDateTextBox().setText(orderDate);
		appModel.customerOrderTextBox().setText(customerOrder);
		appModel.searchOrderButton().click();
		delayFor(5);
	}

	public void openDollarDollar(String customerPONumber){
		logger.info("openDollarDollar("+customerPONumber+")");
		int rows = Integer.valueOf(appModel.orderDetailsGrid().getProperty("RowCount").toString());
		for(int i=0;i<rows;i++){
			System.out.println(appModel.orderDetailsGrid().getCellValue(i, "BSTNK"));
			if(appModel.orderDetailsGrid().getCellValue(i, "BSTNK").contentEquals(customerPONumber)){
				appModel.orderDetailsGrid().doubleClick(i, "IC_PRICE");
				appModel.orderDetailsGrid().clickCurrentCell();
				//appModel.orderDetailsGrid().doubleClick(i, "IC_PRICE");
				break;
			}
		}
	}
	public boolean  verifyOrderDetailsGrid(String vpName){
		logger.info("verifyOrderDetailsGrid("+vpName+")");
		boolean result = false;
		ITestData testData = appModel.orderDetailsGrid().getTestData("list");
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		return result;
	}
	public void clickFollowOnFunctions(){
		logger.info("clickFollowOnFunctions()");
		appModel.followOnFunctionsButton().click();
		delayFor(3);
		
	}
	public void clickUnderReview(){
		logger.info("clickUnderReview()");
		appModel.underReviewButton().click();
		delayFor(3);
		
	}
	public void clickCreatePO(){
		logger.info("clickUnderReview()");
		appModel.createPOButton().click();
		delayFor(3);
		
	}

	
	public void clickSourceofSupply(){
		logger.info("clickSourceofSupply()");
		appModel.sourceofSupplyButton().click();
		delayFor(3);
		
	}
	public void selectRowByCustomerPO(String customerPONumber){
		logger.info("selectRowByCustomerPO("+customerPONumber+")");
		int rows = Integer.valueOf(appModel.orderDetailsGrid().getProperty("RowCount").toString());
		for(int i=0;i<rows;i++){
			//Customer PO# = BSTNK in the app
			if(appModel.orderDetailsGrid().getCellValue(i, "BSTNK").contentEquals(customerPONumber)){
				appModel.orderDetailsGrid().setSelectedRows(i+"");
			}
		}
	}
	public String getPONumber(String customerPONumber){
		logger.info("getPONumber("+customerPONumber+")");
		String poNumber = null;
		int rows = Integer.valueOf(appModel.orderDetailsGrid().getProperty("RowCount").toString());
		for(int i=0;i<rows;i++){
			//Customer PO# = BSTNK in the app
			if(appModel.orderDetailsGrid().getCellValue(i, "BSTNK").contentEquals(customerPONumber)){
				poNumber = appModel.orderDetailsGrid().getCellValue(i, "EBELN").toString();
				System.out.println("PO Number :"+ poNumber);
			}
		}
			
		
		return poNumber;
	}
	public void openPONumber(String customerPONumber){
		logger.info("openPONumber("+customerPONumber+")");
		int rows = Integer.valueOf(appModel.orderDetailsGrid().getProperty("RowCount").toString());
		for(int i=0;i<rows;i++){
			//Customer PO# = BSTNK in the app
			if(appModel.orderDetailsGrid().getCellValue(i, "BSTNK").contentEquals(customerPONumber)){
				appModel.orderDetailsGrid().setCurrentCell(i, "EBELN");
				
				appModel.orderDetailsGrid().clearSelection();
				appModel.orderDetailsGrid().clickCurrentCell();
				delayFor(3);
				break;
			}
		}
			
	}
	public void openCustomerPO(String customerPONumber){
		logger.info("openCustomerPO("+customerPONumber+")");
		int rows = Integer.valueOf(appModel.orderDetailsGrid().getProperty("RowCount").toString());
		for(int i=0;i<rows;i++){
			//Customer PO# = BSTNK in the app
			if(appModel.orderDetailsGrid().getCellValue(i, "BSTNK").contentEquals(customerPONumber)){
				appModel.orderDetailsGrid().setCurrentCell(i, "BSTNK");
				
				appModel.orderDetailsGrid().clearSelection();
				appModel.orderDetailsGrid().clickCurrentCell();
				delayFor(3);
				break;
			}
		}
			
	}

	public void checkSLByCustomerPO(String customerPONumber){
		logger.info("openDollarDollar("+customerPONumber+")");
		int rows = Integer.valueOf(appModel.orderDetailsGrid().getProperty("RowCount").toString());
		for(int i=0;i<rows;i++){
			if(appModel.orderDetailsGrid().getCellValue(i, "BSTNK").contentEquals(customerPONumber)){
				appModel.orderDetailsGrid().modifyCheckbox(i, "UPDATEX", true);
			}
		}
	}
}
